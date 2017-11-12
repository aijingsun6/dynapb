package org.alking.dynapb;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.LongBuffer;

class PbVarInt implements PbValue {

    private static final int BIT_7 = 1 << 7;
    private static final int BYTES_MAX = 10;
    private long value;

    public long getValue() {
        return value;
    }

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
        if (this.value < 0L) {
            return BYTES_MAX;
        }
        long v = this.value;
        int size = 0;
        while (v >= BIT_7) {
            size++;
            v = v >> 7;
        }
        return size;
    }

    @Override
    public void read(byte[] data, int offset) {
        if (data == null) {
            throw new IllegalArgumentException("data");
        }
        if (data.length < offset) {
            throw new IllegalArgumentException("data");
        }
        // check var int format
        boolean allMoreThan128 = true;
        int size = 1;
        for (int i = 0; i < BYTES_MAX; i++) {
            if ((data[offset + i] & 0xff) < BIT_7) {
                allMoreThan128 = false;
                break;
            } else {
                size += 1;
            }
        }
        if (allMoreThan128) {
            throw new IllegalArgumentException("var int format error");
        }
        this.value = 0;
        for (int i = 0; i < size; i++) {
            this.value += (long) (0x7f & data[offset + i]) << (7 * i);
        }
    }

    @Override
    public void write(byte[] data, int offset) {
        long v = this.value;
        if(v == 0){
            data[offset] = 0;
            return;
        }
        long tmp = 0L;
        int i = 0;
        if (this.value > 0) {

            while (v > 0) {
                tmp = 0xff & v;
                if (tmp < BIT_7) {
                    data[offset + i] = (byte) tmp;
                    return;
                } else {
                    data[offset + i] = (byte) tmp;
                    i ++;
                    v = v >> 7;
                }
            }
        }else {

            ByteBuffer bb = ByteBuffer.allocate(8);
            bb.order( ByteOrder.LITTLE_ENDIAN );
            LongBuffer lb = bb.asLongBuffer();
            lb.put(v);
            v = v & Long.MAX_VALUE;
            for (int j = 0; j < BYTES_MAX - 1;j++){
                data[offset + i] = (byte) (v | 0x80);
                i ++;
                v = v >> 7;
            }
            data[ offset + i]  = 1;
        }
    }

    @Override
    public void read(InputStream is) {

    }

    @Override
    public void write(OutputStream os) {

    }

    @Override
    public void read(ByteBuffer buffer) {

    }

    @Override
    public void write(ByteBuffer buffer) {

    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}