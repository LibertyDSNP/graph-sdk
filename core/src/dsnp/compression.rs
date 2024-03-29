//! Definition and implementations to support compression/decompression to store data
use dsnp_graph_config::errors::{DsnpGraphError, DsnpGraphResult};
use log::Level;
use log_result_proc_macro::log_result_err;
use miniz_oxide::{
	deflate::{compress_to_vec, CompressionLevel},
	inflate::decompress_to_vec,
};

/// Common trait for different compression algorithms
pub trait CompressionBehavior {
	/// compress the input
	fn compress(obj: &[u8]) -> DsnpGraphResult<Vec<u8>>;

	/// decompress the input
	fn decompress(data: &[u8]) -> DsnpGraphResult<Vec<u8>>;
}

/// Deflate Compression algorithm
pub struct DeflateCompression;

impl CompressionBehavior for DeflateCompression {
	#[log_result_err(Level::Info)]
	fn compress(obj: &[u8]) -> DsnpGraphResult<Vec<u8>> {
		Ok(compress_to_vec(obj, CompressionLevel::BestCompression as u8))
	}

	#[log_result_err(Level::Info)]
	fn decompress(data: &[u8]) -> DsnpGraphResult<Vec<u8>> {
		let val =
			decompress_to_vec(data).map_err(|e| DsnpGraphError::DecompressError(e.to_string()))?;
		Ok(val)
	}
}

#[cfg(test)]
mod test {
	use super::*;

	#[test]
	fn deflate_compression_should_compress_and_decompress() {
		let data = vec![
			2u8, 1, 0, 23, 5, 82, 100, 56, 23, 120, 200, 250, 140, 83, 98, 0, 10, 234, 88, 23, 54,
			23, 23, 109, 198, 111, 70, 2, 89, 2u8, 1, 0, 23, 5, 82, 100, 56, 1, 120, 200, 250, 140,
			83, 98, 0, 10, 234, 88, 23, 54, 23, 23, 109, 198, 111, 70, 2, 89,
		];

		let compressed = DeflateCompression::compress(&data).unwrap();
		let decompressed = DeflateCompression::decompress(&compressed).unwrap();

		assert_eq!(decompressed, data);
	}
}
