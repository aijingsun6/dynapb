// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/pb.proto

package org.alking.dynapb;

public final class DynaPb {
  private DynaPb() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface FooOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Foo)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int64 var_int = 1;</code>
     */
    boolean hasVarInt();
    /**
     * <code>optional int64 var_int = 1;</code>
     */
    long getVarInt();

    /**
     * <code>optional float var_float = 2;</code>
     */
    boolean hasVarFloat();
    /**
     * <code>optional float var_float = 2;</code>
     */
    float getVarFloat();

    /**
     * <code>optional double var_double = 3;</code>
     */
    boolean hasVarDouble();
    /**
     * <code>optional double var_double = 3;</code>
     */
    double getVarDouble();

    /**
     * <code>optional bool var_bool = 4;</code>
     */
    boolean hasVarBool();
    /**
     * <code>optional bool var_bool = 4;</code>
     */
    boolean getVarBool();
  }
  /**
   * Protobuf type {@code Foo}
   */
  public static final class Foo extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:Foo)
      FooOrBuilder {
    // Use Foo.newBuilder() to construct.
    private Foo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private Foo(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final Foo defaultInstance;
    public static Foo getDefaultInstance() {
      return defaultInstance;
    }

    public Foo getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private Foo(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              varInt_ = input.readInt64();
              break;
            }
            case 21: {
              bitField0_ |= 0x00000002;
              varFloat_ = input.readFloat();
              break;
            }
            case 25: {
              bitField0_ |= 0x00000004;
              varDouble_ = input.readDouble();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              varBool_ = input.readBool();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.alking.dynapb.DynaPb.internal_static_Foo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.alking.dynapb.DynaPb.internal_static_Foo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.alking.dynapb.DynaPb.Foo.class, org.alking.dynapb.DynaPb.Foo.Builder.class);
    }

    public static com.google.protobuf.Parser<Foo> PARSER =
        new com.google.protobuf.AbstractParser<Foo>() {
      public Foo parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Foo(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<Foo> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int VAR_INT_FIELD_NUMBER = 1;
    private long varInt_;
    /**
     * <code>optional int64 var_int = 1;</code>
     */
    public boolean hasVarInt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int64 var_int = 1;</code>
     */
    public long getVarInt() {
      return varInt_;
    }

    public static final int VAR_FLOAT_FIELD_NUMBER = 2;
    private float varFloat_;
    /**
     * <code>optional float var_float = 2;</code>
     */
    public boolean hasVarFloat() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional float var_float = 2;</code>
     */
    public float getVarFloat() {
      return varFloat_;
    }

    public static final int VAR_DOUBLE_FIELD_NUMBER = 3;
    private double varDouble_;
    /**
     * <code>optional double var_double = 3;</code>
     */
    public boolean hasVarDouble() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional double var_double = 3;</code>
     */
    public double getVarDouble() {
      return varDouble_;
    }

    public static final int VAR_BOOL_FIELD_NUMBER = 4;
    private boolean varBool_;
    /**
     * <code>optional bool var_bool = 4;</code>
     */
    public boolean hasVarBool() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional bool var_bool = 4;</code>
     */
    public boolean getVarBool() {
      return varBool_;
    }

    private void initFields() {
      varInt_ = 0L;
      varFloat_ = 0F;
      varDouble_ = 0D;
      varBool_ = false;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt64(1, varInt_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeFloat(2, varFloat_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeDouble(3, varDouble_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBool(4, varBool_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, varInt_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(2, varFloat_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeDoubleSize(3, varDouble_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(4, varBool_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static org.alking.dynapb.DynaPb.Foo parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.alking.dynapb.DynaPb.Foo parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.alking.dynapb.DynaPb.Foo parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.alking.dynapb.DynaPb.Foo parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.alking.dynapb.DynaPb.Foo parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.alking.dynapb.DynaPb.Foo parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static org.alking.dynapb.DynaPb.Foo parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static org.alking.dynapb.DynaPb.Foo parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static org.alking.dynapb.DynaPb.Foo parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.alking.dynapb.DynaPb.Foo parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(org.alking.dynapb.DynaPb.Foo prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Foo}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Foo)
        org.alking.dynapb.DynaPb.FooOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.alking.dynapb.DynaPb.internal_static_Foo_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.alking.dynapb.DynaPb.internal_static_Foo_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.alking.dynapb.DynaPb.Foo.class, org.alking.dynapb.DynaPb.Foo.Builder.class);
      }

      // Construct using org.alking.dynapb.DynaPb.Foo.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        varInt_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        varFloat_ = 0F;
        bitField0_ = (bitField0_ & ~0x00000002);
        varDouble_ = 0D;
        bitField0_ = (bitField0_ & ~0x00000004);
        varBool_ = false;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.alking.dynapb.DynaPb.internal_static_Foo_descriptor;
      }

      public org.alking.dynapb.DynaPb.Foo getDefaultInstanceForType() {
        return org.alking.dynapb.DynaPb.Foo.getDefaultInstance();
      }

      public org.alking.dynapb.DynaPb.Foo build() {
        org.alking.dynapb.DynaPb.Foo result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public org.alking.dynapb.DynaPb.Foo buildPartial() {
        org.alking.dynapb.DynaPb.Foo result = new org.alking.dynapb.DynaPb.Foo(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.varInt_ = varInt_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.varFloat_ = varFloat_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.varDouble_ = varDouble_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.varBool_ = varBool_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.alking.dynapb.DynaPb.Foo) {
          return mergeFrom((org.alking.dynapb.DynaPb.Foo)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.alking.dynapb.DynaPb.Foo other) {
        if (other == org.alking.dynapb.DynaPb.Foo.getDefaultInstance()) return this;
        if (other.hasVarInt()) {
          setVarInt(other.getVarInt());
        }
        if (other.hasVarFloat()) {
          setVarFloat(other.getVarFloat());
        }
        if (other.hasVarDouble()) {
          setVarDouble(other.getVarDouble());
        }
        if (other.hasVarBool()) {
          setVarBool(other.getVarBool());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        org.alking.dynapb.DynaPb.Foo parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.alking.dynapb.DynaPb.Foo) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long varInt_ ;
      /**
       * <code>optional int64 var_int = 1;</code>
       */
      public boolean hasVarInt() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int64 var_int = 1;</code>
       */
      public long getVarInt() {
        return varInt_;
      }
      /**
       * <code>optional int64 var_int = 1;</code>
       */
      public Builder setVarInt(long value) {
        bitField0_ |= 0x00000001;
        varInt_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 var_int = 1;</code>
       */
      public Builder clearVarInt() {
        bitField0_ = (bitField0_ & ~0x00000001);
        varInt_ = 0L;
        onChanged();
        return this;
      }

      private float varFloat_ ;
      /**
       * <code>optional float var_float = 2;</code>
       */
      public boolean hasVarFloat() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional float var_float = 2;</code>
       */
      public float getVarFloat() {
        return varFloat_;
      }
      /**
       * <code>optional float var_float = 2;</code>
       */
      public Builder setVarFloat(float value) {
        bitField0_ |= 0x00000002;
        varFloat_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional float var_float = 2;</code>
       */
      public Builder clearVarFloat() {
        bitField0_ = (bitField0_ & ~0x00000002);
        varFloat_ = 0F;
        onChanged();
        return this;
      }

      private double varDouble_ ;
      /**
       * <code>optional double var_double = 3;</code>
       */
      public boolean hasVarDouble() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional double var_double = 3;</code>
       */
      public double getVarDouble() {
        return varDouble_;
      }
      /**
       * <code>optional double var_double = 3;</code>
       */
      public Builder setVarDouble(double value) {
        bitField0_ |= 0x00000004;
        varDouble_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional double var_double = 3;</code>
       */
      public Builder clearVarDouble() {
        bitField0_ = (bitField0_ & ~0x00000004);
        varDouble_ = 0D;
        onChanged();
        return this;
      }

      private boolean varBool_ ;
      /**
       * <code>optional bool var_bool = 4;</code>
       */
      public boolean hasVarBool() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional bool var_bool = 4;</code>
       */
      public boolean getVarBool() {
        return varBool_;
      }
      /**
       * <code>optional bool var_bool = 4;</code>
       */
      public Builder setVarBool(boolean value) {
        bitField0_ |= 0x00000008;
        varBool_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bool var_bool = 4;</code>
       */
      public Builder clearVarBool() {
        bitField0_ = (bitField0_ & ~0x00000008);
        varBool_ = false;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:Foo)
    }

    static {
      defaultInstance = new Foo(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:Foo)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Foo_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_Foo_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016proto/pb.proto\"O\n\003Foo\022\017\n\007var_int\030\001 \001(\003" +
      "\022\021\n\tvar_float\030\002 \001(\002\022\022\n\nvar_double\030\003 \001(\001\022" +
      "\020\n\010var_bool\030\004 \001(\010B\033\n\021org.alking.dynapbB\006" +
      "DynaPb"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_Foo_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Foo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_Foo_descriptor,
        new java.lang.String[] { "VarInt", "VarFloat", "VarDouble", "VarBool", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
