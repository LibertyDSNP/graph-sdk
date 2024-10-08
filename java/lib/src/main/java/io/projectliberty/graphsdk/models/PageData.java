// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bridge/common/protos/input.proto

package io.projectliberty.graphsdk.models;

/**
 * Protobuf type {@code PageData}
 */
public final class PageData extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:PageData)
    PageDataOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PageData.newBuilder() to construct.
  private PageData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PageData() {
    content_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new PageData();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.projectliberty.graphsdk.models.Input.internal_static_PageData_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.projectliberty.graphsdk.models.Input.internal_static_PageData_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.projectliberty.graphsdk.models.PageData.class, io.projectliberty.graphsdk.models.PageData.Builder.class);
  }

  public static final int PAGE_ID_FIELD_NUMBER = 1;
  private int pageId_ = 0;
  /**
   * <code>uint32 page_id = 1;</code>
   * @return The pageId.
   */
  @java.lang.Override
  public int getPageId() {
    return pageId_;
  }

  public static final int CONTENT_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString content_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes content = 2;</code>
   * @return The content.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getContent() {
    return content_;
  }

  public static final int CONTENT_HASH_FIELD_NUMBER = 3;
  private int contentHash_ = 0;
  /**
   * <code>uint32 content_hash = 3;</code>
   * @return The contentHash.
   */
  @java.lang.Override
  public int getContentHash() {
    return contentHash_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (pageId_ != 0) {
      output.writeUInt32(1, pageId_);
    }
    if (!content_.isEmpty()) {
      output.writeBytes(2, content_);
    }
    if (contentHash_ != 0) {
      output.writeUInt32(3, contentHash_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (pageId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(1, pageId_);
    }
    if (!content_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, content_);
    }
    if (contentHash_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(3, contentHash_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.projectliberty.graphsdk.models.PageData)) {
      return super.equals(obj);
    }
    io.projectliberty.graphsdk.models.PageData other = (io.projectliberty.graphsdk.models.PageData) obj;

    if (getPageId()
        != other.getPageId()) return false;
    if (!getContent()
        .equals(other.getContent())) return false;
    if (getContentHash()
        != other.getContentHash()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PAGE_ID_FIELD_NUMBER;
    hash = (53 * hash) + getPageId();
    hash = (37 * hash) + CONTENT_FIELD_NUMBER;
    hash = (53 * hash) + getContent().hashCode();
    hash = (37 * hash) + CONTENT_HASH_FIELD_NUMBER;
    hash = (53 * hash) + getContentHash();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.projectliberty.graphsdk.models.PageData parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.projectliberty.graphsdk.models.PageData parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.projectliberty.graphsdk.models.PageData parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.projectliberty.graphsdk.models.PageData parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.projectliberty.graphsdk.models.PageData parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.projectliberty.graphsdk.models.PageData parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.projectliberty.graphsdk.models.PageData parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.projectliberty.graphsdk.models.PageData parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.projectliberty.graphsdk.models.PageData parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.projectliberty.graphsdk.models.PageData parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.projectliberty.graphsdk.models.PageData parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.projectliberty.graphsdk.models.PageData parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.projectliberty.graphsdk.models.PageData prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code PageData}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:PageData)
      io.projectliberty.graphsdk.models.PageDataOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.projectliberty.graphsdk.models.Input.internal_static_PageData_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.projectliberty.graphsdk.models.Input.internal_static_PageData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.projectliberty.graphsdk.models.PageData.class, io.projectliberty.graphsdk.models.PageData.Builder.class);
    }

    // Construct using io.projectliberty.graphsdk.models.PageData.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      pageId_ = 0;
      content_ = com.google.protobuf.ByteString.EMPTY;
      contentHash_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.projectliberty.graphsdk.models.Input.internal_static_PageData_descriptor;
    }

    @java.lang.Override
    public io.projectliberty.graphsdk.models.PageData getDefaultInstanceForType() {
      return io.projectliberty.graphsdk.models.PageData.getDefaultInstance();
    }

    @java.lang.Override
    public io.projectliberty.graphsdk.models.PageData build() {
      io.projectliberty.graphsdk.models.PageData result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.projectliberty.graphsdk.models.PageData buildPartial() {
      io.projectliberty.graphsdk.models.PageData result = new io.projectliberty.graphsdk.models.PageData(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(io.projectliberty.graphsdk.models.PageData result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.pageId_ = pageId_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.content_ = content_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.contentHash_ = contentHash_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.projectliberty.graphsdk.models.PageData) {
        return mergeFrom((io.projectliberty.graphsdk.models.PageData)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.projectliberty.graphsdk.models.PageData other) {
      if (other == io.projectliberty.graphsdk.models.PageData.getDefaultInstance()) return this;
      if (other.getPageId() != 0) {
        setPageId(other.getPageId());
      }
      if (other.getContent() != com.google.protobuf.ByteString.EMPTY) {
        setContent(other.getContent());
      }
      if (other.getContentHash() != 0) {
        setContentHash(other.getContentHash());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              pageId_ = input.readUInt32();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 18: {
              content_ = input.readBytes();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              contentHash_ = input.readUInt32();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private int pageId_ ;
    /**
     * <code>uint32 page_id = 1;</code>
     * @return The pageId.
     */
    @java.lang.Override
    public int getPageId() {
      return pageId_;
    }
    /**
     * <code>uint32 page_id = 1;</code>
     * @param value The pageId to set.
     * @return This builder for chaining.
     */
    public Builder setPageId(int value) {
      
      pageId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>uint32 page_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPageId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      pageId_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString content_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes content = 2;</code>
     * @return The content.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getContent() {
      return content_;
    }
    /**
     * <code>bytes content = 2;</code>
     * @param value The content to set.
     * @return This builder for chaining.
     */
    public Builder setContent(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      content_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>bytes content = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearContent() {
      bitField0_ = (bitField0_ & ~0x00000002);
      content_ = getDefaultInstance().getContent();
      onChanged();
      return this;
    }

    private int contentHash_ ;
    /**
     * <code>uint32 content_hash = 3;</code>
     * @return The contentHash.
     */
    @java.lang.Override
    public int getContentHash() {
      return contentHash_;
    }
    /**
     * <code>uint32 content_hash = 3;</code>
     * @param value The contentHash to set.
     * @return This builder for chaining.
     */
    public Builder setContentHash(int value) {
      
      contentHash_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>uint32 content_hash = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearContentHash() {
      bitField0_ = (bitField0_ & ~0x00000004);
      contentHash_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:PageData)
  }

  // @@protoc_insertion_point(class_scope:PageData)
  private static final io.projectliberty.graphsdk.models.PageData DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.projectliberty.graphsdk.models.PageData();
  }

  public static io.projectliberty.graphsdk.models.PageData getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PageData>
      PARSER = new com.google.protobuf.AbstractParser<PageData>() {
    @java.lang.Override
    public PageData parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<PageData> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PageData> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.projectliberty.graphsdk.models.PageData getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

