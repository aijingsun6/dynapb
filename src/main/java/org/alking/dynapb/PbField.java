package org.alking.dynapb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * for java field
 */
final class PbField implements PbRW {

    private int fieldNum;

    private WireType wireType;

    private PbRW value;

    int getFieldNum() {
        return fieldNum;
    }

    PbRW getValue() {
        return value;
    }

    PbField() {

    }

    PbField(int fieldNum, PbRW value) {
        this.fieldNum = fieldNum;
        this.value = value;
        this.wireType = value.wireType();
    }

    @Override
    public WireType wireType() {
        return this.wireType;
    }

    @Override
    public int size() {
        int comp = this.fieldNum << 3 | this.wireType.getCode();
        PbVarInt head = new PbVarInt(comp);
        if (this.wireType.equals(WireType.BYTES)) {
            PbVarInt len = new PbVarInt(this.value.size());
            return head.size() + len.size() + this.value.size();
        }
        return head.size() + this.value.size();
    }

    @Override
    public int read(byte[] data, int offset) {
        PbVarInt head = new PbVarInt();
        int read = head.read(data, offset);
        this.fieldNum = head.intValue() >> 3;
        this.wireType = WireType.valueOf(head.intValue() & 0x07);
        if (this.wireType.equals(WireType.VARINT)) {
            this.value = new PbVarInt();
            int read2 = this.value.read(data, offset + read);
            return read + read2;
        }
        if (this.wireType.equals(WireType.BIT32)) {
            this.value = new PbBit32();
            int read2 = this.value.read(data, offset + read);
            return read + read2;
        }
        if (this.wireType.equals(WireType.BIT64)) {
            this.value = new PbBit64();
            int read2 = this.value.read(data, offset + read);
            return read + read2;
        }
        // Length-delimited
        PbVarInt len = new PbVarInt();
        int read2 = len.read(data, offset + read);
        this.value = new PbBytes(len.intValue());
        int read3 = this.value.read(data, offset + read + read2);
        return read + read2 + read3;
    }

    @Override
    public int read(InputStream is) throws IOException {
        PbVarInt head = new PbVarInt();
        int read = head.read(is);
        this.fieldNum = head.intValue() >> 3;
        this.wireType = WireType.valueOf(head.intValue() & 0x07);
        if (this.wireType.equals(WireType.VARINT)) {
            this.value = new PbVarInt();
            int read2 = this.value.read(is);
            return read + read2;
        }
        if (this.wireType.equals(WireType.BIT32)) {
            this.value = new PbBit32();
            int read2 = this.value.read(is);
            return read + read2;
        }
        if (this.wireType.equals(WireType.BIT64)) {
            this.value = new PbBit64();
            int read2 = this.value.read(is);
            return read + read2;
        }
        // Length-delimited
        PbVarInt len = new PbVarInt();
        int read2 = len.read(is);
        this.value = new PbBytes(len.intValue());
        int read3 = this.value.read(is);
        return read + read2 + read3;
    }

    @Override
    public int read(ByteBuffer buffer) {
        PbVarInt head = new PbVarInt();
        int read = head.read(buffer);
        this.fieldNum = head.intValue() >> 3;
        this.wireType = WireType.valueOf(head.intValue() & 0x07);
        if (this.wireType.equals(WireType.VARINT)) {
            this.value = new PbVarInt();
            int read2 = this.value.read(buffer);
            return read + read2;
        }
        if (this.wireType.equals(WireType.BIT32)) {
            this.value = new PbBit32();
            int read2 = this.value.read(buffer);
            return read + read2;
        }
        if (this.wireType.equals(WireType.BIT64)) {
            this.value = new PbBit64();
            int read2 = this.value.read(buffer);
            return read + read2;
        }
        // Length-delimited
        PbVarInt len = new PbVarInt();
        int read2 = len.read(buffer);
        this.value = new PbBytes(len.intValue());
        int read3 = this.value.read(buffer);
        return read + read2 + read3;
    }

    @Override
    public int write(byte[] data, int offset) {
        int comp = this.fieldNum << 3 | this.wireType.getCode();
        PbVarInt head = new PbVarInt(comp);
        if (this.wireType.equals(WireType.BYTES)) {
            PbVarInt len = new PbVarInt(this.value.size());
            int writeHead = head.write(data, offset);
            int writeLen = len.write(data, offset + writeHead);
            int writeData = this.value.write(data, offset + writeHead + writeLen);
            return writeHead + writeLen + writeData;
        }
        int writeHead = head.write(data, offset);
        int writeData = this.value.write(data, offset + writeHead);
        return writeHead + writeData;
    }

    @Override
    public int write(OutputStream os) throws IOException {
        int comp = this.fieldNum << 3 | this.wireType.getCode();
        PbVarInt head = new PbVarInt(comp);
        if (this.wireType.equals(WireType.BYTES)) {
            PbVarInt len = new PbVarInt(this.value.size());
            int writeHead = head.write(os);
            int writeLen = len.write(os);
            int writeData = this.value.write(os);
            return writeHead + writeLen + writeData;
        }
        int writeHead = head.write(os);
        int writeData = this.value.write(os);
        return writeHead + writeData;
    }

    @Override
    public int write(ByteBuffer buffer) {
        int comp = this.fieldNum << 3 | this.wireType.getCode();
        PbVarInt head = new PbVarInt(comp);
        if (this.wireType.equals(WireType.BYTES)) {
            PbVarInt len = new PbVarInt(this.value.size());
            int writeHead = head.write(buffer);
            int writeLen = len.write(buffer);
            int writeData = this.value.write(buffer);
            return writeHead + writeLen + writeData;
        }
        int writeHead = head.write(buffer);
        int writeData = this.value.write(buffer);
        return writeHead + writeData;
    }
}