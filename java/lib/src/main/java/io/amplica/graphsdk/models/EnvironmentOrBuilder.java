// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protos/output.proto

package io.amplica.graphsdk.models;

public interface EnvironmentOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Environment)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.EnvironmentType environment_type = 1;</code>
   * @return The enum numeric value on the wire for environmentType.
   */
  int getEnvironmentTypeValue();
  /**
   * <code>.EnvironmentType environment_type = 1;</code>
   * @return The environmentType.
   */
  io.amplica.graphsdk.models.EnvironmentType getEnvironmentType();

  /**
   * <code>optional .Config config = 2;</code>
   * @return Whether the config field is set.
   */
  boolean hasConfig();
  /**
   * <code>optional .Config config = 2;</code>
   * @return The config.
   */
  io.amplica.graphsdk.models.Config getConfig();
  /**
   * <code>optional .Config config = 2;</code>
   */
  io.amplica.graphsdk.models.ConfigOrBuilder getConfigOrBuilder();
}
