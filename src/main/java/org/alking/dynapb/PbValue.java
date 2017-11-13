package org.alking.dynapb;

abstract class PbValue implements PbRW {

    abstract boolean boolValue();

    abstract int intValue();

    abstract long longValue();

    abstract float floatValue();

    abstract double doubleValue();

    abstract String stringValue();

    public static PbValue valueOf(boolean b){
        return new PbVarInt(b);
    }

    public static PbValue valueOf(int i){
        return new PbVarInt(i);
    }

    public static PbValue valueOf(long l){
        return new PbVarInt(l);
    }

    public static PbValue valueOf(float f){
        return new PbBit32(f);
    }

    public static PbValue valueOf(double d){
        return new PbBit64(d);
    }

    public static PbValue valueOf(String s){
        return new PbBytes(s);
    }

    public static PbValue valueOf(byte[] data, int offset, int size){
        return new PbBytes(data, offset, size);
    }
}