.DEFAULT_GOAL := all

.PHONY: check
check:
	@echo "Running Cargo check..."
	@cargo check --all --all-features --all-targets

.PHONY: test
test:
	@echo "Running Cargo test..."
	@cargo test --all --all-targets

.PHONY: clippy
clippy:
	@echo "Running Cargo clippy..."
	@cargo clippy --all --all-features --all-targets -- -D warnings

.PHONY: fmt
fmt:
	@echo "Running Cargo fmt..."
	@cargo fmt --all -- --check

.PHONY: build
build:
	@echo "Running Cargo build..."
	@cargo build --all --all-features --all-targets

.PHONY: doc
doc:
	@echo "Running Cargo doc..."
	@RUSTDOCFLAGS="--enable-index-page --check -Zunstable-options" cargo doc --no-deps --all-features

.PHONY: clean
clean:
	@echo "Running Cargo clean..."
	@cargo clean

.PHONY: capacities
capacities:
	@echo "Generating graph page capacities..."
	@cargo test --features=calculate-page-capacity calculate_page_capacities

.PHONY: all
all: check test clippy fmt build doc

.PHONY: ci-local
ci-local: all
