//! Errors for graph-sdk crate
//!
use super::*;
use thiserror::Error;
pub type DsnpGraphResult<T> = std::result::Result<T, DsnpGraphError>;

#[derive(Debug, Error)]
pub enum DsnpGraphError {
	#[error(transparent)]
	AvroError(#[from] apache_avro::Error),

	#[error("Add of duplicate connection in another page detected")]
	DuplicateConnectionDetected,

	#[error("Calling apply_prids in non private friendship graph!")]
	CallToPridsInPublicGraph,

	#[error("Call to private friends in non private graph")]
	CallToPrivateFriendsInPublicGraph,

	#[error("Connection from {0} to {1} already exists!")]
	ConnectionAlreadyExists(DsnpUserId, DsnpUserId),

	#[error("Connection from {0} to {1} does not exists!")]
	ConnectionDoesNotExist(DsnpUserId, DsnpUserId),

	#[error("Connection not found")]
	ConnectionNotFound,

	#[error("Failed to decompress: {0}")]
	DecompressError(String),

	#[error("Failed to decrypt: {0}")]
	DecryptionError(String),

	#[error("Duplicate update events detected")]
	DuplicateUpdateEvents,

	#[error("Event exists")]
	EventExists,

	#[error("Failed to encrypt: {0}")]
	EncryptionError(String),

	#[error("Failed to retrieve graph page")]
	FailedToRetrieveGraphPage,

	#[error("Failed to acquire read lock on state manager")]
	FailedtoReadLockStateManager,

	#[error("Failed to acquire write lock on state manager")]
	FailedtoWriteLockStateManager,

	#[error("Graph is full")]
	GraphIsFull,

	#[error("GraphState instance is full")]
	GraphStateIsFull,

	#[error("Invalid schema id: {0}")]
	InvalidSchemaId(SchemaId),

	#[error("Invalid Page ID: {0}")]
	InvalidPageId(PageId),

	#[error("Invalid private schema id")]
	InvalidPrivateSchemaId,

	#[error("Invalid public key")]
	InvalidPublicKey,

	#[error("Invalid secret key")]
	InvalidSecretKey,

	#[error("Imported key not found for user {0} and id {1}")]
	ImportedKeyNotFound(DsnpUserId, String),

	#[error("Incorrect connection type: {0}")]
	IncorrectConnectionType(String),

	#[error("Incompatible privacy type for blob export")]
	IncompatiblePrivacyTypeForBlobExport,

	#[error("Key derivation error: {0}")]
	KeyDerivationError(String),

	#[error("No pris imported for user: {0}")]
	NoPrisImportedForUser(DsnpUserId),

	#[error("No public key found for user: {0}")]
	NoPublicKeyFoundForUser(DsnpUserId),

	#[error("No resolved active key found")]
	NoResolvedActiveKeyFound,

	#[error("New page for existing page id")]
	NewPageForExistingPageId,

	#[error("Given public key already exists: {0}")]
	PublicKeyAlreadyExists(String),

	#[error("Public key not compatible with secret key")]
	PublicKeyNotCompatibleWithSecretKey,

	#[error(
		"page_id: {0}, prids len should be equal to connections len (connections: {1}, prids: {2})"
	)]
	PridsLenShouldBeEqualToConnectionsLen(PageId, usize, usize),

	#[error(" unsupported schema: {0}")]
	UnsupportedSchema(SchemaId),

	#[error(transparent)]
	Unknown(#[from] anyhow::Error),

	#[error("User graph for {0} is not imported")]
	UserGraphNotImported(DsnpUserId),

	#[error("Unable to decrypt private graph with any of the imported keys")]
	UnableToDecryptGraphChunkWithAnyKey,
}

impl DsnpGraphError {
	pub fn error_code(&self) -> i32 {
		match self {
			DsnpGraphError::AvroError { .. } => 1,
			DsnpGraphError::DuplicateConnectionDetected => 2,
			DsnpGraphError::CallToPridsInPublicGraph => 3,
			DsnpGraphError::CallToPrivateFriendsInPublicGraph => 4,
			DsnpGraphError::ConnectionAlreadyExists(..) => 5,
			DsnpGraphError::ConnectionDoesNotExist(..) => 6,
			DsnpGraphError::ConnectionNotFound => 7,
			DsnpGraphError::DecompressError(_) => 8,
			DsnpGraphError::DecryptionError(_) => 9,
			DsnpGraphError::DuplicateUpdateEvents => 11,
			DsnpGraphError::EventExists => 12,
			DsnpGraphError::EncryptionError(_) => 13,
			DsnpGraphError::FailedToRetrieveGraphPage => 14,
			DsnpGraphError::FailedtoReadLockStateManager => 15,
			DsnpGraphError::FailedtoWriteLockStateManager => 16,
			DsnpGraphError::GraphIsFull => 17,
			DsnpGraphError::GraphStateIsFull => 18,
			DsnpGraphError::InvalidSchemaId(_) => 19,
			DsnpGraphError::InvalidPageId(_) => 20,
			DsnpGraphError::InvalidPrivateSchemaId => 21,
			DsnpGraphError::InvalidPublicKey => 22,
			DsnpGraphError::InvalidSecretKey => 23,
			DsnpGraphError::ImportedKeyNotFound(..) => 24,
			DsnpGraphError::IncorrectConnectionType(_) => 25,
			DsnpGraphError::IncompatiblePrivacyTypeForBlobExport => 26,
			DsnpGraphError::KeyDerivationError(_) => 27,
			DsnpGraphError::NoPrisImportedForUser(_) => 28,
			DsnpGraphError::NoPublicKeyFoundForUser(_) => 29,
			DsnpGraphError::NoResolvedActiveKeyFound => 30,
			DsnpGraphError::NewPageForExistingPageId => 31,
			DsnpGraphError::PublicKeyAlreadyExists(_) => 32,
			DsnpGraphError::PublicKeyNotCompatibleWithSecretKey => 33,
			DsnpGraphError::PridsLenShouldBeEqualToConnectionsLen(..) => 35,
			DsnpGraphError::UnsupportedSchema(_) => 37,
			DsnpGraphError::Unknown(..) => 38,
			DsnpGraphError::UserGraphNotImported(_) => 39,
			DsnpGraphError::UnableToDecryptGraphChunkWithAnyKey => 40,
		}
	}
}
