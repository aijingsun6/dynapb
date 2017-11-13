package org.alking.dynapb;

final class PbField {

    private int fieldNum;

    private WireType wireType;

    private PbValue value;

    public int getFieldNum() {
        return fieldNum;
    }

    public void setFieldNum(int fieldNum) {
        this.fieldNum = fieldNum;
    }

    public WireType getWireType() {
        return wireType;
    }

    public void setWireType(WireType wireType) {
        this.wireType = wireType;
    }

    public PbValue getValue() {
        return value;
    }

    public void setValue(PbValue value) {
        this.value = value;
    }

    public PbField(int fieldNum, WireType wireType, PbValue value) {
        this.fieldNum = fieldNum;
        this.wireType = wireType;
        this.value = value;
    }
}