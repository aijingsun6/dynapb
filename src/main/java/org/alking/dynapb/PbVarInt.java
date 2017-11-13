package org.alking.dynapb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

final class PbVarInt extends PbValue {

    private static final int BIT_7 = 1 << 7;

    private static final int BYTES_MAX = 10;
    /**
     * thread safe
     */
    private static final ThreadLocal<byte[]> localBytes = new ThreadLocal<byte[]>(){
        @Override
        protected byte[] initialValue() {
            return new byte[16];
        }
    };
    private long value;

    PbVarInt() {

    }

    PbVarInt(boolean b) {
        this.value = b ? 1L : 0L;
    }

    PbVarInt(int i) {
        this.value = i;
    }

    PbVarInt(long l) {
        this.value = l;
    }

    @Override
    public int size() {
        if(this.value == 0){
            return 1;
        }

        int size = 0;
        long v = this.value;
        // value > 0
        if(this.value > 0){
            while (v > 0) {
                size ++;
                v = v >> 7;
            }
            return size;
        }
        // value < 0
        return  BYTES_MAX;
    }

    @Override
    public boolean boolValue() {
        return this.value != 0;
    }

    @Override
    public int intValue() {
        if(this.value > Integer.MAX_VALUE || this.value < Integer.MIN_VALUE){
            throw new PbException(String.format("%d out of int range", this.value));
        }
        return (int)this.value;
    }

    @Override
    public long longValue() {
        return this.value;
    }

    @Override
    public float floatValue() {
        throw new PbException("wire type not match");
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
        if (data == null) {
            throw new PbException("data is null");
        }
        if (data.length < offset) {
            throw new PbException("data not enough");
        }
        // check var int format
        boolean allMoreThan128 = true;
        int size = 0;
        for (int i = 0; i < BYTES_MAX; i++) {
            size ++;
            if ((data[offset + i] & 0xff) < BIT_7) {
                // find first value < 128
                allMoreThan128 = false;
                break;
            }
        }
        if (allMoreThan128) {
            throw new PbException("var int format error");
        }
        this.value = 0;
        for (int i = 0; i < size; i++) {
            this.value += (long) (0x7f & data[offset + i]) << (7 * i);
        }
        return size;
    }

    @Override
    public int write(final byte[] data, final int offset) {
        // value = 0
        if(this.value == 0){
            data[offset] = 0;
            return 1;
        }

        int size = 0;
        long v = this.value;
        // value > 0
        if(this.value > 0){
            while (v > 0) {
                if(v < BIT_7){
                    data[offset+size] = (byte) (0x7f & v);
                }else {
                    data[offset+size] = (byte) ( 0x80 | (0x7f & v));
                }
                size ++;
                v = v >> 7;
            }
            return size;
        }
        // value < 0
        v = v & Long.MAX_VALUE;
        for (int j = 0; j < BYTES_MAX - 1;j++){
            data[offset + size] = (byte) (v | 0x80);
            size ++;
            v = v >> 7;
        }
        data[offset + size] = 1;
        return BYTES_MAX;
    }

    @Override
    public int read(final InputStream is, final int limit) throws IOException {
        if(is == null){
            throw new IllegalArgumentException("input stream is null.");
        }
        this.value = 0L;
        int offset = 0;
        int size = 0;
        int tmp;
        byte[] tBytes = localBytes.get();
        while ( is.read(tBytes, offset, 1) > 0 ){
            if(size > BYTES_MAX){
                throw new PbException("var int format error");
            }
            tmp = tBytes[offset] & 0xff;
            this.value += (long) (0x7f & tmp) << (7 * size);
            size ++;
            if(tmp < BIT_7){
                return size;
            }
        }
        return size;
    }

    @Override
    public int write(final OutputStream os) throws IOException {
        byte[] tBytes = localBytes.get();
        int size = write(tBytes, 0);
        os.write(tBytes,0,size);
        return size;

    }

    @Override
    public int read(final ByteBuffer buffer, final int limit) {
        if(buffer == null){
            throw new IllegalArgumentException("input stream is null.");
        }
        this.value = 0L;
        int size = 0;
        int tmp;
        while ( buffer.hasRemaining() ){
            if(size > BYTES_MAX){
                throw new PbException("var int format error");
            }
            tmp = buffer.get() & 0xff;
            this.value += (long) (0x7f & tmp) << (7 * size);
            size ++;
            if(tmp < BIT_7){
                return size;
            }
        }
        return size;
    }

    @Override
    public int write(final ByteBuffer buffer) {
        byte[] tBytes = localBytes.get();
        int size = write(tBytes, 0);
        buffer.put(tBytes, 0, size);
        return size;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public WireType wireType() {
        return WireType.VARINT;
    }
}