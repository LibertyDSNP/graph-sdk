// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protos/output.proto

package io.amplica.graphsdk.models;

/**
 * Protobuf enum {@code ConnectionType}
 */
public enum ConnectionType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>FollowPublic = 0;</code>
   */
  FollowPublic(0),
  /**
   * <code>FollowPrivate = 1;</code>
   */
  FollowPrivate(1),
  /**
   * <code>FriendshipPublic = 2;</code>
   */
  FriendshipPublic(2),
  /**
   * <code>FriendshipPrivate = 3;</code>
   */
  FriendshipPrivate(3),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>FollowPublic = 0;</code>
   */
  public static final int FollowPublic_VALUE = 0;
  /**
   * <code>FollowPrivate = 1;</code>
   */
  public static final int FollowPrivate_VALUE = 1;
  /**
   * <code>FriendshipPublic = 2;</code>
   */
  public static final int FriendshipPublic_VALUE = 2;
  /**
   * <code>FriendshipPrivate = 3;</code>
   */
  public static final int FriendshipPrivate_VALUE = 3;


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
  public static ConnectionType valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static ConnectionType forNumber(int value) {
    switch (value) {
      case 0: return FollowPublic;
      case 1: return FollowPrivate;
      case 2: return FriendshipPublic;
      case 3: return FriendshipPrivate;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<ConnectionType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      ConnectionType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<ConnectionType>() {
          public ConnectionType findValueByNumber(int number) {
            return ConnectionType.forNumber(number);
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
    return io.amplica.graphsdk.models.Output.getDescriptor().getEnumTypes().get(1);
  }

  private static final ConnectionType[] VALUES = values();

  public static ConnectionType valueOf(
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

  private ConnectionType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:ConnectionType)
}

