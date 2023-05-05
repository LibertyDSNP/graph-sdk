use crate::{bindings::*, utils::*};
use dsnp_graph_core::{
	api::api::{GraphAPI, GraphState},
	dsnp::dsnp_types::DsnpUserId,
};

#[no_mangle]
pub extern "C" fn print_hello_graph() {
	println!("Hello, Graph!");
}

// Singleton for GraphState
static mut GRAPH_STATE: Option<GraphState> = None;

// Intialize GraphState
#[no_mangle]
pub unsafe extern "C" fn initialize_graph_state(environment: *const Environment) -> bool {
	let environment = &*environment;
	let rust_environment = environment_from_ffi(environment);
	let graph_state = GraphState::new(rust_environment);
	GRAPH_STATE = Some(graph_state);
	true
}

// Intialize GraphState with capacity
#[no_mangle]
pub unsafe extern "C" fn initialize_graph_state_with_capacity(
	environment: *const Environment,
	capacity: usize,
) -> bool {
	let environment = &*environment;
	let rust_environment = environment_from_ffi(environment);
	let graph_state = GraphState::with_capacity(rust_environment, capacity);
	GRAPH_STATE = Some(graph_state);
	true
}

// Get Capacity
#[no_mangle]
pub unsafe extern "C" fn get_graph_capacity() -> usize {
	if GRAPH_STATE.is_none() {
		return 0
	}
	GRAPH_STATE.as_ref().unwrap().capacity()
}

// State contains user graph
#[no_mangle]
pub unsafe extern "C" fn graph_contains_user(user_id: *const DsnpUserId) -> bool {
	if GRAPH_STATE.is_none() {
		return false
	}
	let user_id = &*user_id;
	GRAPH_STATE.as_ref().unwrap().contains_user_graph(user_id)
}

// Count of users in current graph
#[no_mangle]
pub unsafe extern "C" fn graph_users_count() -> usize {
	if GRAPH_STATE.is_none() {
		return 0
	}
	GRAPH_STATE.as_ref().unwrap().len()
}

// Remove user
#[no_mangle]
pub unsafe extern "C" fn graph_remove_user(user_id: *const DsnpUserId) -> bool {
	if GRAPH_STATE.is_none() {
		return false
	}
	let user_id = &*user_id;
	GRAPH_STATE.as_mut().unwrap().remove_user_graph(user_id);
	true
}

//
#[no_mangle]
pub unsafe extern "C" fn graph_import_users_data(
	payloads: *const ImportBundle,
	payloads_len: usize,
) -> bool {
	if GRAPH_STATE.is_none() {
		return false
	}
	let payloads = std::slice::from_raw_parts(payloads, payloads_len);
	let payloads = payloads_from_ffi(payloads);
	GRAPH_STATE.as_mut().unwrap().import_users_data(payloads).is_ok()
}

// Graph export updates fn export_updates(&mut self) -> Result<Vec<Update>> {
#[no_mangle]
pub unsafe extern "C" fn graph_export_updates() -> *mut Update {
	if GRAPH_STATE.is_none() {
		return std::ptr::null_mut()
	}
	let updates = GRAPH_STATE.as_mut().unwrap().export_updates().unwrap();
	let ffi_updates = updates_to_ffi(updates);
	let _updates_len = ffi_updates.len();
	let updates_ptr = Box::into_raw(Box::new(ffi_updates));
	updates_ptr as *mut Update
}

// Graph apply actions 	fn apply_actions(&mut self, action: &[Action]) -> Result<()>;
#[no_mangle]
pub unsafe extern "C" fn graph_apply_actions(actions: *const Action, actions_len: usize) -> bool {
	if GRAPH_STATE.is_none() {
		return false
	}
	let actions = std::slice::from_raw_parts(actions, actions_len);
	let actions = actions_from_ffi(&actions);
	GRAPH_STATE.as_mut().unwrap().apply_actions(&actions).is_ok()
}

// Free GraphState
#[no_mangle]
pub unsafe extern "C" fn free_graph_state() -> bool {
	GRAPH_STATE = None;
	true
}
