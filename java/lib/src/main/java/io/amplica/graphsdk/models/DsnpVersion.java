// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protos/output.proto

package io.amplica.graphsdk.models;

/**
 * Protobuf enum {@code DsnpVersion}
 */
public enum DsnpVersion
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>Version1_0 = 0;</code>
   */
  Version1_0(0),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>Version1_0 = 0;</code>
   */
  public static final int Version1_0_VALUE = 0;


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
  public static DsnpVersion valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static DsnpVersion forNumber(int value) {
    switch (value) {
      case 0: return Version1_0;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<DsnpVersion>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      DsnpVersion> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<DsnpVersion>() {
          public DsnpVersion findValueByNumber(int number) {
            return DsnpVersion.forNumber(number);
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
    return io.amplica.graphsdk.models.Output.getDescriptor().getEnumTypes().get(2);
  }

  private static final DsnpVersion[] VALUES = values();

  public static DsnpVersion valueOf(
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

  private DsnpVersion(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:DsnpVersion)
}

