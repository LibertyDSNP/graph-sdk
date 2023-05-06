use dsnp_graph_config::{ConnectionType, DsnpVersion, GraphKeyType, SchemaId};
use dsnp_graph_core::dsnp::{
	api_types::{Connection, PageHash, PageId},
	dsnp_types::{DsnpGraphEdge, DsnpUserId},
};
use libc::{c_void, size_t};

/// KeyData wrapper
#[repr(C)]
pub struct KeyData {
	pub index: u16,
	pub content: *mut u8,
	pub content_len: size_t,
}

/// Key Pair wrapper
#[repr(C)]
pub struct GraphKeyPair {
	/// key pair type
	pub key_type: GraphKeyType,

	/// public key raw
	pub public_key: *const u8,

	/// length of the public key
	pub public_key_len: size_t,

	/// secret key raw
	pub secret_key: *const u8,

	/// length of the secret key
	pub secret_key_len: size_t,
}

// Raw page of Graph (or Key) data
#[repr(C)]
pub struct PageData {
	// Id of the page
	pub page_id: PageId,

	// raw content of page data
	pub content: *mut u8,
	pub content_len: size_t,

	// hash value of content
	pub content_hash: PageHash,
}

// GraphAPI wrapper
#[repr(C)]
pub struct DsnpKeys {
	pub dsnp_user_id: DsnpUserId,
	pub keys_hash: PageHash,
	pub keys: *mut KeyData,
	pub keys_len: size_t,
}

// ImportBundle wrapper
#[repr(C)]
pub struct ImportBundle {
	/// graph owner dsnp user id
	pub dsnp_user_id: DsnpUserId,

	/// Schema id of imported data
	pub schema_id: SchemaId,

	/// key pairs associated with this graph which is used for encryption and PRI generation
	pub key_pairs: *mut GraphKeyPair,
	pub key_pairs_len: size_t,

	/// published dsnp keys associated with this dsnp user
	pub dsnp_keys: DsnpKeys,

	/// Page data containing the social graph retrieved from chain
	pub pages: *mut PageData,
	pub pages_len: size_t,
}

// ActionType wrapper
#[repr(C)]
pub enum ActionType {
	// Connect action
	Connect,
	// Disconnect action
	Disconnect,
}

// ConnectAction wrapper
#[repr(C)]
pub struct ConnectAction {
	// dsnp user id
	pub owner_dsnp_user_id: DsnpUserId,
	// connection
	pub connection: Connection,
}

// DisconnectAction wrapper
#[repr(C)]
pub struct DisconnectAction {
	// dsnp user id
	pub owner_dsnp_user_id: DsnpUserId,
	// connection
	pub connection: Connection,
}

// Action wrapper
#[repr(C)]
pub struct Action {
	// action type
	pub action_type: ActionType,
	// connect action
	pub action: *mut c_void,
}

#[repr(C)]
pub struct PersistPage {
	/// owner of the social graph
	pub owner_dsnp_user_id: DsnpUserId,

	/// Schema id of imported data
	pub schema_id: SchemaId,

	/// page id associated with changed page
	pub page_id: PageId,

	/// previous hash value is used to avoid updating a stale state
	pub prev_hash: PageHash,

	/// social graph page data
	pub payload: *mut u8,
	pub payload_len: size_t,
}

#[repr(C)]
pub struct DeletePage {
	/// owner of the social graph
	pub owner_dsnp_user_id: DsnpUserId,

	/// Schema id of removed data
	pub schema_id: SchemaId,

	/// page id associated with changed page
	pub page_id: PageId,

	/// previous hash value is used to avoid updating a stale state
	pub prev_hash: PageHash,
}

#[repr(C)]
pub struct AddKey {
	/// owner of the social graph
	pub owner_dsnp_user_id: DsnpUserId,

	/// previous hash value is used to avoid updating a stale state
	pub prev_hash: PageHash,

	/// social graph page data
	pub payload: *mut u8,
	pub payload_len: size_t,
}

#[repr(C)]
pub enum Update {
	Persist(PersistPage),
	Delete(DeletePage),
	Add(AddKey),
}

#[repr(C)]
pub struct SchemaConfig {
	pub dsnp_version: DsnpVersion,
	pub connection_type: ConnectionType,
}

#[repr(C)]
pub struct SchemaConfigTuple {
	pub schema_id: SchemaId,
	pub schema_config: SchemaConfig,
}

#[repr(C)]
pub struct Config {
	pub sdk_max_users_graph_size: u32,
	pub sdk_max_stale_friendship_days: u32,
	pub max_graph_page_size_bytes: u32,
	pub max_page_id: u32,
	pub max_key_page_size_bytes: u32,
	pub schema_map_len: size_t,
	pub schema_map: *mut SchemaConfigTuple,
	pub dsnp_versions_len: size_t,
	pub dsnp_versions: *mut DsnpVersion,
}

#[repr(C)]
pub enum EnvironmentType {
	Mainnet,
	Rococo,
	Dev,
}

#[repr(C)]
pub struct Environment {
	pub environment_type: EnvironmentType,
	pub config: Config, // This field will only be used when environment_type is Dev.
}

#[repr(C)]
pub struct GraphConnections {
	pub connections: *mut DsnpGraphEdge,
	pub len: usize,
}

#[repr(C)]
pub struct GraphConnectionsWithoutKeys {
	pub connections: *mut DsnpUserId,
	pub len: usize,
}
