// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bridge/common/protos/input.proto

package io.amplica.graphsdk.models;

/**
 * Protobuf enum {@code GraphKeyType}
 */
public enum GraphKeyType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>X25519 = 0;</code>
   */
  X25519(0),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>X25519 = 0;</code>
   */
  public static final int X25519_VALUE = 0;


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
  public static GraphKeyType valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static GraphKeyType forNumber(int value) {
    switch (value) {
      case 0: return X25519;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<GraphKeyType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      GraphKeyType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<GraphKeyType>() {
          public GraphKeyType findValueByNumber(int number) {
            return GraphKeyType.forNumber(number);
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
    return io.amplica.graphsdk.models.Input.getDescriptor().getEnumTypes().get(0);
  }

  private static final GraphKeyType[] VALUES = values();

  public static GraphKeyType valueOf(
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

  private GraphKeyType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:GraphKeyType)
}

