// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bridge/common/protos/input.proto

package io.amplica.graphsdk.models;

public interface PageDataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PageData)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint32 page_id = 1;</code>
   * @return The pageId.
   */
  int getPageId();

  /**
   * <code>bytes content = 2;</code>
   * @return The content.
   */
  com.google.protobuf.ByteString getContent();

  /**
   * <code>uint32 content_hash = 3;</code>
   * @return The contentHash.
   */
  int getContentHash();
}
