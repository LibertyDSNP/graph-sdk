// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bridge/common/protos/input.proto

package io.projectliberty.graphsdk.models;

public interface DsnpKeysOrBuilder extends
    // @@protoc_insertion_point(interface_extends:DsnpKeys)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint64 dsnp_user_id = 1;</code>
   * @return The dsnpUserId.
   */
  long getDsnpUserId();

  /**
   * <code>uint32 keys_hash = 2;</code>
   * @return The keysHash.
   */
  int getKeysHash();

  /**
   * <code>repeated .KeyData keys = 3;</code>
   */
  java.util.List<io.projectliberty.graphsdk.models.KeyData> 
      getKeysList();
  /**
   * <code>repeated .KeyData keys = 3;</code>
   */
  io.projectliberty.graphsdk.models.KeyData getKeys(int index);
  /**
   * <code>repeated .KeyData keys = 3;</code>
   */
  int getKeysCount();
  /**
   * <code>repeated .KeyData keys = 3;</code>
   */
  java.util.List<? extends io.projectliberty.graphsdk.models.KeyDataOrBuilder> 
      getKeysOrBuilderList();
  /**
   * <code>repeated .KeyData keys = 3;</code>
   */
  io.projectliberty.graphsdk.models.KeyDataOrBuilder getKeysOrBuilder(
      int index);
}
