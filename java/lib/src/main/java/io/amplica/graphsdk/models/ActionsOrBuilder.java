// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bridge/common/protos/input.proto

package io.amplica.graphsdk.models;

public interface ActionsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Actions)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .Actions.Action actions = 1;</code>
   */
  java.util.List<io.amplica.graphsdk.models.Actions.Action> 
      getActionsList();
  /**
   * <code>repeated .Actions.Action actions = 1;</code>
   */
  io.amplica.graphsdk.models.Actions.Action getActions(int index);
  /**
   * <code>repeated .Actions.Action actions = 1;</code>
   */
  int getActionsCount();
  /**
   * <code>repeated .Actions.Action actions = 1;</code>
   */
  java.util.List<? extends io.amplica.graphsdk.models.Actions.ActionOrBuilder> 
      getActionsOrBuilderList();
  /**
   * <code>repeated .Actions.Action actions = 1;</code>
   */
  io.amplica.graphsdk.models.Actions.ActionOrBuilder getActionsOrBuilder(
      int index);

  /**
   * <code>optional .Actions.ActionOptions options = 2;</code>
   * @return Whether the options field is set.
   */
  boolean hasOptions();
  /**
   * <code>optional .Actions.ActionOptions options = 2;</code>
   * @return The options.
   */
  io.amplica.graphsdk.models.Actions.ActionOptions getOptions();
  /**
   * <code>optional .Actions.ActionOptions options = 2;</code>
   */
  io.amplica.graphsdk.models.Actions.ActionOptionsOrBuilder getOptionsOrBuilder();
}
