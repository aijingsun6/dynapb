package org.alking.dynapb;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

final class PbBit32 implements PbValue {

    private static final int SIZE = 4;

    private static final ThreadLocal<byte[]> localBytes = new ThreadLocal<byte[]>(){
        @Override
        protected byte[] initialValue() {
            return new byte[SIZE];
        }
    };

    private float value;

    PbBit32() {
    }

    PbBit32(float value) {
        this.value = value;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public WireType type() {
        return WireType.BIT32;
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
        return this.value;
    }

    @Override
    public double doubleValue() {
        throw new PbException("wire type not match");
    }

    @Override
    public String stringValue() {
        throw new PbException("wire type not match");
    }

    @Override
    public int read(final byte[] data, final int offset, final int limit) {
        if(data == null || data.length < offset + SIZE){
            throw new IllegalArgumentException("data error");
        }
        ByteBuffer bf = ByteBuffer.allocate(SIZE);
        bf.order( ByteOrder.LITTLE_ENDIAN );
        FloatBuffer fb = bf.asFloatBuffer();
        bf.put(data, offset, SIZE);
        this.value = fb.get();
        return SIZE;
    }

    @Override
    public int write(final byte[] data, final int offset) {
        ByteBuffer bf = ByteBuffer.allocate(SIZE);
        bf.order( ByteOrder.LITTLE_ENDIAN );
        FloatBuffer fb = bf.asFloatBuffer();
        fb.put(this.value);
        bf.get(data, offset, SIZE);
        return SIZE;
    }

    @Override
    public int read(final InputStream is, final int limit) throws IOException {
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
        FloatBuffer bf = buffer.asFloatBuffer();
        bf.put(this.value);
        return SIZE;
    }
}
