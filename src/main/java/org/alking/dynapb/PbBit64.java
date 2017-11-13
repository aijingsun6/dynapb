package org.alking.dynapb;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

final class PbBit64 implements PbValue {

    private static final int SIZE = 8;

    private static final ThreadLocal<byte[]> localBytes = new ThreadLocal<byte[]>(){
        @Override
        protected byte[] initialValue() {
            byte[] data = new byte[SIZE];
            return data;
        }
    };
    private double value;

    PbBit64() {
    }

    PbBit64(double value) {
        this.value = value;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public WireType type() {
        return WireType.BIT64;
    }

    @Override
    public boolean boolValue() {
        throw new PbException("wire type not match");
    }

    @Override
    public int intValue() {
        throw new PbException("wire type not match");
    }

    @Override
    public long longValue() {
        throw new PbException("wire type not match");
    }

    @Override
    public float floatValue() {
        throw new PbException("wire type not match");
    }

    @Override
    public double doubleValue() {
        return this.value;
    }

    @Override
    public String stringValue() {
        throw new PbException("wire type not match");
    }

    @Override
    public int read(final byte[] data, final int offset, final int limit) {
        if(data == null){
            throw new PbException("data is null");
        }
        if(data.length < offset + SIZE){
            throw new IllegalArgumentException("data error");
        }
        ByteBuffer bf = ByteBuffer.allocate(SIZE);
        bf.order(ByteOrder.LITTLE_ENDIAN);
        DoubleBuffer db = bf.asDoubleBuffer();
        bf.put(data, offset, SIZE);
        this.value = db.get();
        return SIZE;
    }

    @Override
    public int write(final byte[] data, final int offset) {
        ByteBuffer bf = ByteBuffer.allocate(SIZE);
        bf.order(ByteOrder.LITTLE_ENDIAN);
        DoubleBuffer db = bf.asDoubleBuffer();
        db.put(this.value);
        bf.get(data, offset, SIZE);
        return SIZE;
    }

    @Override
    public int read(final InputStream is, int limit) throws IOException {
        byte[] tBytes = localBytes.get();
        IOUtils.read(is, tBytes, 0, SIZE);
        return this.read(tBytes, 0, limit);
    }

    @Override
    public int write(final OutputStream os) throws IOException {
        byte[] tBytes = localBytes.get();
        int size = this.write(tBytes,0);
        os.write(tBytes,0, size);
        return size;
    }

    @Override
    public int read(final ByteBuffer buffer, final int limit) {
        FloatBuffer bf = buffer.asFloatBuffer();
        this.value = bf.get();
        return SIZE;
    }

    @Override
    public int write(final ByteBuffer buffer) {
        DoubleBuffer df = buffer.asDoubleBuffer();
        df.put(this.value);
        return SIZE;
    }
}
