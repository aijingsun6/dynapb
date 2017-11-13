package org.alking.dynapb;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class PbBit32 implements PbValue {

    private static final int SIZE = 4;

    private static final ThreadLocal<byte[]> localBytes = new ThreadLocal<byte[]>(){
        @Override
        protected byte[] initialValue() {
            byte[] data = new byte[SIZE];
            return data;
        }
    };

    private static final ThreadLocal<ByteBuffer> localBF = new ThreadLocal<ByteBuffer>(){
        @Override
        protected ByteBuffer initialValue() {
            ByteBuffer bb = ByteBuffer.allocate(SIZE);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            return bb;
        }
    };

    private float value;

    public PbBit32() {
    }

    public PbBit32(float value) {
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
    public int read(byte[] data, int offset) {
        if(data == null || data.length < offset + SIZE){
            throw new IllegalArgumentException("data error");
        }
        ByteBuffer bf = localBF.get();
        FloatBuffer fb = bf.asFloatBuffer();
        bf.put(data, offset, SIZE);
        this.value = fb.get();
        return SIZE;
    }

    @Override
    public int write(byte[] data, int offset) {
        ByteBuffer bf = localBF.get();
        FloatBuffer fb = bf.asFloatBuffer();
        fb.put(this.value);
        bf.get(data, offset, SIZE);
        return SIZE;
    }

    @Override
    public int read(InputStream is) throws IOException {
        byte[] tBytes = localBytes.get();
        int offset = 0;
        int size = 0;
        int read = 0;
        while (size < SIZE){
            read = is.read(tBytes, offset, SIZE - size);
            offset += read;
            size += read;
        }
        return this.read(tBytes, 0);
    }

    @Override
    public int write(OutputStream os) throws IOException {
        byte[] tBytes = localBytes.get();
        int size = this.write(tBytes,0);
        os.write(tBytes,0, size);
        return size;
    }

    @Override
    public int read(ByteBuffer buffer) {
        FloatBuffer bf = buffer.asFloatBuffer();
        this.value = bf.get();
        return SIZE;
    }

    @Override
    public int write(ByteBuffer buffer) {
        FloatBuffer bf = buffer.asFloatBuffer();
        bf.put(this.value);
        return SIZE;
    }
}
