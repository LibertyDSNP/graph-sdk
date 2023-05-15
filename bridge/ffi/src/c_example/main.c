#include <stdio.h>
#include "dsnp_graph_sdk_ffi.h"

#define ASSERT(condition, message) \
    do { \
        if (!(condition)) { \
            printf("Assertion failed: %s\n", message); \
            return 1; \
        } \
    } while (0)

int test_graph_sdk_ffi() {
    Environment environment;
    
    // Set the environment type
    environment.tag = Dev;
    
    // Set the values of the Config struct
    environment.dev.sdk_max_users_graph_size = 100;
    environment.dev.sdk_max_stale_friendship_days = 30;
    environment.dev.max_graph_page_size_bytes = 4096;
    environment.dev.max_page_id = 1000;
    environment.dev.max_key_page_size_bytes = 2048;
    environment.dev.schema_map_len = 0;
    environment.dev.schema_map = NULL;
    environment.dev.dsnp_versions_len = 0;
    environment.dev.dsnp_versions = NULL;

    GraphState* graph_state = initialize_graph_state(&environment);
    ASSERT(graph_state != NULL, "Graph state initialization failed");

    DsnpUserId user_id;
    // Set the value of the user_id
    // ...

    bool contains_user = graph_contains_user(graph_state, &user_id);
    ASSERT(!contains_user, "Graph should not contain user before import");

    size_t users_count = graph_users_count(graph_state);
    ASSERT(users_count == 0, "Number of users in the graph should be zero");

    // Test import and export functions
    ImportBundle import_bundle;
    // Set the values of the import_bundle struct
    // ...

    // Failing because of dummy environment
    bool imported = graph_import_users_data(graph_state, &import_bundle, 1);
    ASSERT(!imported, "Graph data import failed");

    GraphUpdates graph_updates = graph_export_updates(graph_state);
    ASSERT(graph_updates.updates_len == 0, "Graph export updates failed");

    // Test connection retrieval functions
    GraphConnections connections = graph_get_connections_for_user(graph_state, &user_id, NULL, true);
    ASSERT(connections.connections_len == 0, "Failed to get connections for user");

    GraphConnectionsWithoutKeys connections_without_keys = graph_get_connections_without_keys(graph_state);
    ASSERT(connections_without_keys.connections_len == 0, "Failed to get connections without keys");

    GraphConnections one_sided_connections = graph_get_one_sided_private_friendship_connections(graph_state, &user_id);
    ASSERT(one_sided_connections.connections_len == 0, "Failed to get one-sided private friendship connections");

    DsnpPublicKeys public_keys = graph_get_public_keys(graph_state, &user_id);
    ASSERT(public_keys.keys_len == 0, "Failed to get dsnp public keys");

    // Clean up and free the graph state
    free_graph_state(graph_state);

    return 0;
}

int main() {
    // Run the test for the graph SDK FFI
    int result = test_graph_sdk_ffi();
    if (result == 0) {
        printf("All tests passed!\n");
    } else {
        printf("Some tests failed!\n");
    }

    return result;
}
