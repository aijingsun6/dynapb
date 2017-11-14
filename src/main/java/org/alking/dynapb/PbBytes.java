package org.alking.dynapb;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * for {@link String} and byte[]
 */
final class PbBytes extends PbValue {

    private byte[] bytes;

    private int offset;

    private int size;

    byte[] getBytes() {
        return bytes;
    }

    int getOffset() {
        return offset;
    }

    int getSize() {
        return size;
    }

    public PbBytes() {
    }

    public PbBytes(String s) {
        if (s == null) {
            throw new PbException("string is null");
        }
        this.bytes = s.getBytes(Charset.forName("UTF-8"));
        this.offset = 0;
        this.size = this.bytes.length;
    }

    public PbBytes(byte[] data, int offset, int size) {
        if (data == null) {
            throw new PbException("data is null");
        }
        if (data.length < (offset + size)) {
            throw new PbException("data length not enough");
        }
        this.bytes = data;
        this.offset = offset;
        this.size = size;
    }

    @Override
    public WireType wireType() {
        return WireType.BYTES;
    }

    @Override
    public int size() {
        return this.size;
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
        throw new PbException("wire type not match");
    }

    @Override
    public String stringValue() {
        return new String(this.bytes, this.offset, this.size, Charset.forName("UTF-8"));
    }

    @Override
    public int read(final byte[] data, final int offset, final int limit) {
        if (data == null || data.length < (offset + limit)) {
            throw new PbException("param data error");
        }
        this.bytes = data;
        this.offset = offset;
        this.size = limit;
        return this.size;
    }

    @Override
    public int write(final byte[] data, final int offset) {
        if (data == null || data.length < (offset + this.size)) {
            throw new PbException("param data error");
        }
        System.arraycopy(this.bytes, this.offset, data, offset, this.size);
        return this.size;
    }

    @Override
    public int read(final InputStream is, final int limit) throws IOException {
        this.bytes = new byte[limit];
        this.offset = 0;
        this.size = limit;
        IOUtils.read(is, this.bytes, 0, this.size);
        return this.size;
    }

    @Override
    public int write(final OutputStream os) throws IOException {
        os.write(this.bytes, this.offset, this.size);
        return this.size;
    }

    @Override
    public int read(final ByteBuffer buffer, final int limit) {
        this.bytes = new byte[limit];
        this.offset = 0;
        this.size = limit;
        buffer.get(this.bytes, 0, limit);
        return this.size;
    }

    @Override
    public int write(ByteBuffer buffer) {
        buffer.put(this.bytes, this.offset, this.size);
        return this.size;
    }
}