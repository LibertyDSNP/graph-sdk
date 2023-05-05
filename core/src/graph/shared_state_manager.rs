use crate::{
	dsnp::{
		api_types::{DsnpKeys, KeyData, PageData, PageHash},
		dsnp_configs::{PublicKeyType, SecretKeyType},
		dsnp_types::{DsnpPrid, DsnpPublicKey, DsnpUserId},
		pseudo_relationship_identifier::PridProvider,
		reader_writer::{DsnpReader, DsnpWriter},
		schema::SchemaHandler,
	},
	frequency::Frequency,
};
use anyhow::{Error, Result};
use std::collections::{HashMap, HashSet};

/// A trait that defines all the functionality that a pri manager should implement.
pub trait PriProvider {
	/// imports pri for a user and replaces the older ones if exists
	fn import_pri(&mut self, dsnp_user_id: DsnpUserId, pages: &[PageData]) -> Result<()>;

	/// checks if a pri exist for a specific user
	fn contains(&self, dsnp_user_id: DsnpUserId, prid: DsnpPrid) -> bool;

	fn calculate_prid(
		&self,
		from: DsnpUserId,
		to: DsnpUserId,
		from_secret: SecretKeyType,
	) -> Result<DsnpPrid>;
}

/// A trait that defines all the functionality that a public key provider need to implement.
pub trait PublicKeyProvider {
	/// imports public keys with their hash and details into the provider
	/// will overwrite any existing imported keys for the user and remove any new added keys
	fn import_dsnp_keys(&mut self, keys: &DsnpKeys) -> Result<()>;

	/// adds a new public key to the provider
	fn add_new_key(&mut self, dsnp_user_id: DsnpUserId, public_key: Vec<u8>) -> Result<()>;

	/// exports added new keys to be submitted to chain
	fn export_new_keys(&self) -> Result<Vec<DsnpKeys>>;

	/// get imported and new keys. New keys are appended in the end.
	fn get_all_keys(&self, dsnp_user_id: DsnpUserId) -> Vec<&DsnpPublicKey>;

	/// returns a key by its id
	fn get_key_by_id(&self, dsnp_user_id: DsnpUserId, key_id: u64) -> Option<&DsnpPublicKey>;

	/// returns a key by its public key
	fn get_key_by_public_key(
		&self,
		dsnp_user_id: DsnpUserId,
		public_key: Vec<u8>,
	) -> Option<&DsnpPublicKey>;

	/// returns the active key for a a user to used for encryption
	fn get_active_key(&self, dsnp_user_id: DsnpUserId) -> Option<&DsnpPublicKey>;

	/// returns users that don't have any imported keys
	fn find_users_without_keys(&self, dsnp_user_ids: Vec<DsnpUserId>) -> Vec<DsnpUserId>;
}

#[derive(Debug, Eq, PartialEq)]
pub struct SharedStateManager {
	/// keys are stored sorted by index
	dsnp_user_to_keys: HashMap<DsnpUserId, (Vec<DsnpPublicKey>, PageHash)>,

	/// stores and keeps track of any new key being added
	new_keys: HashMap<DsnpUserId, DsnpPublicKey>,

	/// prids are stored with key_id
	dsnp_user_to_pris: HashMap<DsnpUserId, Vec<(DsnpPrid, u64)>>,
}

impl PriProvider for SharedStateManager {
	fn import_pri(&mut self, dsnp_user_id: DsnpUserId, pages: &[PageData]) -> Result<()> {
		let mut prids = vec![];
		for p in pages {
			let chunk = SchemaHandler::read_private_graph_chunk(&p.content[..])?;
			let mapped: Vec<_> = chunk.prids.iter().map(|p| (p.clone(), chunk.key_id)).collect();
			prids.extend_from_slice(&mapped);
		}
		self.dsnp_user_to_pris.insert(dsnp_user_id, prids);
		Ok(())
	}

	fn contains(&self, dsnp_user_id: DsnpUserId, prid: DsnpPrid) -> bool {
		self.dsnp_user_to_pris
			.get(&dsnp_user_id)
			.unwrap_or(&Vec::new())
			.iter()
			.any(|(p, _)| p == &prid)
	}

	fn calculate_prid(
		&self,
		from: DsnpUserId,
		to: DsnpUserId,
		from_secret: SecretKeyType,
	) -> Result<DsnpPrid> {
		let to_public_key: PublicKeyType = self
			.get_active_key(to)
			.ok_or(Error::msg(format!("No public key found for {}!", to)))?
			.try_into()?;
		let prid = DsnpPrid::create_prid(from, to, &from_secret, &to_public_key)?;
		Ok(prid)
	}
}

impl PublicKeyProvider for SharedStateManager {
	/// importing dsnp keys as they are retrieved from blockchain
	/// sorting indices since ids might not be unique but indices definitely should be
	fn import_dsnp_keys(&mut self, keys: &DsnpKeys) -> Result<()> {
		self.dsnp_user_to_keys.remove(&keys.dsnp_user_id);
		self.new_keys.remove(&keys.dsnp_user_id);

		let mut sorted_keys = keys.keys.clone().to_vec();
		// sorting by index in ascending mode
		sorted_keys.sort();

		let mut dsnp_keys = vec![];
		for key in sorted_keys {
			let mut k = Frequency::read_public_key(&key.content)
				.map_err(|e| Error::msg(format!("failed to deserialize key {:?}", e)))?;
			// key id is the itemized index of the key stored in Frequency
			k.key_id = Some(key.index.into());
			dsnp_keys.push(k);
		}

		self.dsnp_user_to_keys.insert(keys.dsnp_user_id, (dsnp_keys, keys.keys_hash));
		Ok(())
	}

	fn add_new_key(&mut self, dsnp_user_id: DsnpUserId, public_key: Vec<u8>) -> Result<()> {
		let new_key =
			DsnpPublicKey { key: public_key, key_id: Some(self.get_next_key_id(dsnp_user_id)) };

		// making sure it is serializable before adding
		let _ = Frequency::write_public_key(&new_key)
			.map_err(|e| Error::msg(format!("failed to serialize key {:?}", e)))?;

		// only one new key is allowed to be added to a dsnp_user_id at a time
		self.new_keys.insert(dsnp_user_id, new_key.clone());

		Ok(())
	}

	fn export_new_keys(&self) -> Result<Vec<DsnpKeys>> {
		let mut result = vec![];
		for (dsnp_user_id, key) in &self.new_keys {
			result.push(DsnpKeys {
				dsnp_user_id: *dsnp_user_id,
				keys: vec![KeyData {
					content: Frequency::write_public_key(&key)
						.map_err(|e| Error::msg(format!("failed to serialize key {:?}", e)))?,
					// all new keys are assigned a new id so we can unwrap here
					index: key.key_id.unwrap() as u16,
				}],
				keys_hash: self
					.dsnp_user_to_keys
					.get(&dsnp_user_id)
					.expect("Key hash should exist")
					.1,
			});
		}
		Ok(result)
	}

	fn get_all_keys(&self, dsnp_user_id: DsnpUserId) -> Vec<&DsnpPublicKey> {
		let mut all_keys = vec![];
		if let Some((v, _)) = self.dsnp_user_to_keys.get(&dsnp_user_id) {
			all_keys.extend(&v[..]);
		}
		if let Some(k) = self.new_keys.get(&dsnp_user_id) {
			all_keys.push(k)
		}
		all_keys
	}

	fn get_key_by_id(&self, dsnp_user_id: DsnpUserId, key_id: u64) -> Option<&DsnpPublicKey> {
		// get the first key by that id as specified in the spec
		self.get_all_keys(dsnp_user_id)
			.iter()
			.find(|k| k.key_id == Some(key_id))
			.copied()
	}

	fn get_key_by_public_key(
		&self,
		dsnp_user_id: DsnpUserId,
		public_key: Vec<u8>,
	) -> Option<&DsnpPublicKey> {
		// get the first key by that public key as specified in the spec
		self.get_all_keys(dsnp_user_id).iter().find(|k| k.key == public_key).copied()
	}

	fn get_active_key(&self, dsnp_user_id: DsnpUserId) -> Option<&DsnpPublicKey> {
		let last_key = self.get_all_keys(dsnp_user_id).last().cloned();
		if let Some(k) = last_key {
			if let Some(key_id) = k.key_id {
				// get the first key published by that key_id
				return self.get_key_by_id(dsnp_user_id, key_id)
			}
		}
		last_key
	}

	fn find_users_without_keys(&self, dsnp_user_ids: Vec<DsnpUserId>) -> Vec<DsnpUserId> {
		dsnp_user_ids
			.iter()
			.copied()
			.filter(|u| self.dsnp_user_to_keys.get(u).is_none())
			.collect()
	}
}

impl SharedStateManager {
	pub fn new() -> Self {
		Self {
			new_keys: HashMap::new(),
			dsnp_user_to_keys: HashMap::new(),
			dsnp_user_to_pris: HashMap::new(),
		}
	}

	pub fn get_prid_associated_public_keys(
		&self,
		dsnp_user_id: DsnpUserId,
	) -> Result<Vec<PublicKeyType>> {
		// get imported prids for user
		let prids = self
			.dsnp_user_to_pris
			.get(&dsnp_user_id)
			.ok_or(Error::msg(format!("no prids imported for {}", dsnp_user_id)))?;

		// find all unique key_id used in prid calculations
		let key_ids: HashSet<_> = prids.iter().map(|(_, key_id)| *key_id).collect();

		// map key_id to their associated imported public keys
		let public_keys: Result<Vec<_>> = key_ids
			.iter()
			.map(|id| {
				self.get_key_by_id(dsnp_user_id, *id).ok_or(Error::msg(format!(
					"imported key not found for user {} and id {}",
					dsnp_user_id, *id
				)))
			})
			.collect();

		// map DsnpPublicKeys to PublicKeyType
		public_keys?
			.iter()
			.map(|&p| {
				let mapped: Result<PublicKeyType> = p.try_into();
				mapped
			})
			.collect()
	}

	fn get_next_key_id(&self, dsnp_user_id: DsnpUserId) -> u64 {
		self.get_all_keys(dsnp_user_id)
			.iter()
			.filter_map(|key| key.key_id)
			.max()
			.unwrap_or(u64::default()) +
			1
	}

	#[cfg(all(test, not(feature = "calculate-page-capacity")))]
	pub fn import_keys_test(
		&mut self,
		dsnp_user_id: DsnpUserId,
		keys: &[DsnpPublicKey],
		hash: u32,
	) -> Result<()> {
		self.dsnp_user_to_keys.remove(&dsnp_user_id);
		self.new_keys.remove(&dsnp_user_id);

		let dsnp_keys = keys.to_vec();
		self.dsnp_user_to_keys.insert(dsnp_user_id, (dsnp_keys, hash));
		Ok(())
	}

	#[cfg(all(test, not(feature = "calculate-page-capacity")))]
	pub fn import_prids_test(
		&mut self,
		dsnp_user_id: DsnpUserId,
		prids: &[DsnpPrid],
		key_id: u64,
	) -> Result<()> {
		self.dsnp_user_to_pris.remove(&dsnp_user_id);

		let mapped: Vec<_> = prids.iter().map(|p| (p.clone(), key_id)).collect();
		self.dsnp_user_to_pris.insert(dsnp_user_id, mapped);
		Ok(())
	}
}

#[cfg(all(test, not(feature = "calculate-page-capacity")))]
mod tests {
	use super::*;
	use crate::{
		dsnp::{api_types::ResolvedKeyPair, dsnp_configs::KeyPairType},
		tests::helpers::PageDataBuilder,
	};
	use dryoc::keypair::StackKeyPair;
	use dsnp_graph_config::{ConnectionType::Friendship, PrivacyType};

	fn create_dsnp_keys(
		dsnp_user_id: DsnpUserId,
		keys_hash: PageHash,
		key_data: Vec<KeyData>,
	) -> DsnpKeys {
		DsnpKeys { keys_hash, dsnp_user_id, keys: key_data }
	}

	#[test]
	fn pri_provider_import_should_store_prids_as_expected() {
		// arrange
		let mut manager = SharedStateManager::new();
		let prid_1 = DsnpPrid::new(&[1u8, 2, 3, 4, 5, 6, 7, 8]);
		let prid_2 = DsnpPrid::new(&[10u8, 20, 3, 4, 5, 6, 7, 8]);
		let non_existing_prid = DsnpPrid::new(&[2u8, 20, 3, 4, 5, 6, 7, 8]);
		let key_id = 2;
		let pages = PageDataBuilder::new(Friendship(PrivacyType::Private))
			.with_page(1, &vec![(1, 0)], &vec![prid_1.clone()], 0)
			.with_page(2, &vec![(2, 0)], &vec![prid_2.clone()], 0)
			.with_encryption_key(ResolvedKeyPair {
				key_pair: KeyPairType::Version1_0(StackKeyPair::gen()),
				key_id,
			})
			.build();
		let dsnp_user_id = 23;
		let non_existing_user_id = 10;

		// act
		let res = manager.import_pri(dsnp_user_id, &pages);

		// assert
		assert!(res.is_ok());
		assert_eq!(
			manager.dsnp_user_to_pris.get(&dsnp_user_id),
			Some(&vec![(prid_1.clone(), key_id), (prid_2.clone(), key_id)])
		);
		assert_eq!(manager.dsnp_user_to_pris.get(&non_existing_user_id), None);
		assert!(manager.contains(dsnp_user_id, prid_1));
		assert!(manager.contains(dsnp_user_id, prid_2));
		assert!(!manager.contains(dsnp_user_id, non_existing_prid));
	}

	#[test]
	fn pri_provider_import_should_replace_previous_prids() {
		// arrange
		let mut manager = SharedStateManager::new();
		let old_prid = DsnpPrid::new(&[1u8, 2, 3, 4, 5, 6, 7, 8]);
		let key_id = 2;
		let pages = PageDataBuilder::new(Friendship(PrivacyType::Private))
			.with_page(1, &vec![(1, 0)], &vec![old_prid], 0)
			.with_encryption_key(ResolvedKeyPair {
				key_pair: KeyPairType::Version1_0(StackKeyPair::gen()),
				key_id,
			})
			.build();
		let dsnp_user_id = 23;
		manager.import_pri(dsnp_user_id, &pages).expect("should work");
		let new_prid = DsnpPrid::new(&[10u8, 20, 30, 40, 50, 60, 70, 80]);
		let new_pages = PageDataBuilder::new(Friendship(PrivacyType::Private))
			.with_page(1, &vec![(1, 0)], &vec![new_prid.clone()], 0)
			.with_encryption_key(ResolvedKeyPair {
				key_pair: KeyPairType::Version1_0(StackKeyPair::gen()),
				key_id,
			})
			.build();

		// act
		let res = manager.import_pri(dsnp_user_id, &new_pages);

		// assert
		assert!(res.is_ok());
		assert_eq!(manager.dsnp_user_to_pris.get(&dsnp_user_id), Some(&vec![(new_prid, key_id)]));
	}

	#[test]
	fn shared_state_manager_import_should_clean_previous_keys() {
		// arrange
		let mut key_manager = SharedStateManager::new();
		let dsnp_user_id = 23;
		let key_hash = 128;
		let key1 = DsnpPublicKey { key_id: Some(128), key: b"217678127812871812334324".to_vec() };
		let serialized1 = Frequency::write_public_key(&key1).expect("should serialize");
		let old_keys = create_dsnp_keys(
			dsnp_user_id,
			key_hash,
			vec![KeyData { index: 2, content: serialized1 }],
		);
		key_manager.import_dsnp_keys(&old_keys).expect("should work");
		key_manager
			.add_new_key(dsnp_user_id, b"21767812782988871812334324".to_vec())
			.expect("should add new key");

		// act
		let _ = key_manager.import_dsnp_keys(&create_dsnp_keys(dsnp_user_id, key_hash, vec![]));

		// assert
		assert_eq!(key_manager.dsnp_user_to_keys.get(&dsnp_user_id), Some(&(Vec::new(), key_hash)));
		assert_eq!(key_manager.new_keys.get(&dsnp_user_id), None);
	}

	#[test]
	fn shared_state_manager_should_import_and_retrieve_keys_as_expected() {
		// arrange
		let dsnp_user_id = 23;
		let key1 = DsnpPublicKey { key_id: Some(2), key: b"217678127812871812334324".to_vec() };
		let serialized1 = Frequency::write_public_key(&key1).expect("should serialize");
		let key2 = DsnpPublicKey { key_id: Some(1), key: b"217678127812871812334325".to_vec() };
		let serialized2 = Frequency::write_public_key(&key2).expect("should serialize");
		let keys = create_dsnp_keys(
			dsnp_user_id,
			17826,
			vec![
				KeyData { index: 2, content: serialized1 },
				KeyData { index: 1, content: serialized2 },
			],
		);
		let mut key_manager = SharedStateManager::new();

		// act
		let res = key_manager.import_dsnp_keys(&keys);

		// assert
		assert!(res.is_ok());
		assert_eq!(key_manager.get_key_by_id(dsnp_user_id, 1), Some(&key2));
		assert_eq!(key_manager.get_key_by_id(dsnp_user_id, 2), Some(&key1));
		assert_eq!(key_manager.get_key_by_public_key(dsnp_user_id, key1.key.clone()), Some(&key1));
		assert_eq!(key_manager.get_key_by_public_key(dsnp_user_id, key2.key.clone()), Some(&key2));
		assert_eq!(key_manager.get_active_key(dsnp_user_id), Some(&key1));
	}

	#[test]
	fn shared_state_manager_add_new_key_should_store_a_key_with_increased_id() {
		// arrange
		let dsnp_user_id = 2;
		let keys_hash = 233;
		let key1 = DsnpPublicKey { key_id: None, key: b"217678127812871812334324".to_vec() };
		let serialized1 = Frequency::write_public_key(&key1).expect("should serialize");
		let key2 = DsnpPublicKey { key_id: None, key: b"217678127812871812334325".to_vec() };
		let serialized2 = Frequency::write_public_key(&key2).expect("should serialize");
		let keys = create_dsnp_keys(
			dsnp_user_id,
			keys_hash,
			vec![
				KeyData { index: 1, content: serialized1 },
				KeyData { index: 2, content: serialized2 },
			],
		);
		let new_public_key = b"726871hsjgdjsa727821712812".to_vec();
		let expected_added_key = DsnpPublicKey { key_id: Some(3), key: new_public_key.clone() };
		let mut key_manager = SharedStateManager::new();
		key_manager.import_dsnp_keys(&keys).expect("should work");

		// act
		let res = key_manager.add_new_key(dsnp_user_id, new_public_key.clone());

		// assert
		assert!(res.is_ok());
		let active_key = key_manager.get_active_key(dsnp_user_id);
		assert_eq!(active_key, Some(&expected_added_key));
		let export = key_manager.export_new_keys().expect("should work");
		assert_eq!(
			export,
			vec![DsnpKeys {
				keys_hash,
				dsnp_user_id,
				keys: vec![KeyData {
					index: expected_added_key.key_id.unwrap() as u16,
					content: Frequency::write_public_key(&expected_added_key)
						.expect("should write")
				}]
			}]
		);
		assert_eq!(key_manager.get_all_keys(dsnp_user_id).len(), 3);
	}

	#[test]
	fn shared_state_manager_get_key_by_id_should_return_first_key_when_duplicate_ids_exists() {
		// arrange
		let dsnp_user_id = 2;
		let id = 4;
		let key1 = DsnpPublicKey { key_id: Some(id), key: b"217678127812871812334324".to_vec() };
		let serialized1 = Frequency::write_public_key(&key1).expect("should serialize");
		let key2 = DsnpPublicKey { key_id: None, key: b"217678127812871812334325".to_vec() };
		let serialized2 = Frequency::write_public_key(&key2).expect("should serialize");
		let keys = create_dsnp_keys(
			dsnp_user_id,
			233,
			vec![
				KeyData { index: id as u16, content: serialized1 },
				KeyData { index: id as u16, content: serialized2 },
			],
		);
		let mut key_manager = SharedStateManager::new();
		key_manager.import_dsnp_keys(&keys).expect("should work");

		// act
		let res = key_manager.get_key_by_id(dsnp_user_id, id.into());

		// assert
		assert_eq!(res, Some(&key1));
	}
}
