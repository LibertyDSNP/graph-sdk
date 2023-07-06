// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protos/output.proto

package io.amplica.graphsdk.models;

/**
 * Protobuf enum {@code PrivacyType}
 */
public enum PrivacyType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>Public = 0;</code>
   */
  Public(0),
  /**
   * <code>Private = 1;</code>
   */
  Private(1),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>Public = 0;</code>
   */
  public static final int Public_VALUE = 0;
  /**
   * <code>Private = 1;</code>
   */
  public static final int Private_VALUE = 1;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static PrivacyType valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static PrivacyType forNumber(int value) {
    switch (value) {
      case 0: return Public;
      case 1: return Private;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<PrivacyType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      PrivacyType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<PrivacyType>() {
          public PrivacyType findValueByNumber(int number) {
            return PrivacyType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return io.amplica.graphsdk.models.Output.getDescriptor().getEnumTypes().get(0);
  }

  private static final PrivacyType[] VALUES = values();

  public static PrivacyType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private PrivacyType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:PrivacyType)
}

