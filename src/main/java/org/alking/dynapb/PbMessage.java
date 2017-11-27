package org.alking.dynapb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     read data:
 *     =============================
 *     example 1:
 *     byte[] data = ...
 *     int offset = ...
 *     int size = ...
 *     PbMessage msg = new PbMessage(size);
 *     msg.read(data, offset);
 *     ......
 *     =============================
 *     example 2:
 *     InputStream is = ...
 *     PbMessage msg = new PbMessage(0);
 *     msg.read(is);
 *     ......
 *     =============================
 *     example 3:
 *     ByteBuffet buffet = ...
 *     PbMessage msg = new PbMessage(0);
 *     msg.read(buffer);
 *     ......
 *     =============================
 *
 *
 *
 * </pre>
 *
 * <pre>
 *     write data:
 *
 *     PbMessage msg = new PbMessage(0);
 *     PbField f1 = ...
 *     msg.addField(f1);
 *     PbField f2 = ...
 *     msg.addField(f2);
 *     =============================
 *     example 1
 *     byte[] data = ...
 *     int offset = ...
 *     msg.write(data,offset);
 *     =============================
 *     example 2:
 *     OutputStream os = ...
 *     msg.write(os);
 *     =============================
 *     example 3:
 *     ByteBuffer buffer = ...
 *     msg.write(buffer);
 *     =============================
 *
 * </pre>
 */
final class PbMessage implements PbRW {

    private enum Mode {
        /**
         * encode mode
         */
        ENCODE,

        /**
         * decode mode
         */
        DECODE
    }

    private final List<PbField> fields = new ArrayList<>();

    private int size = 0;

    private final Mode mode;

    public List<PbField> getFields() {
        return fields;
    }

    public void addField(PbField field) {
        if(!this.mode.equals(Mode.ENCODE)){
            throw new IllegalArgumentException("mode error");
        }
        this.fields.add(field);
        this.size += field.size();
    }

    public List<PbField> getFieldByFieldNum(int fieldNum){
        List<PbField> result = new ArrayList<>();
        for(PbField f: this.fields){
            if(f.getFieldNum() == fieldNum){
                result.add(f);
            }
        }
        return result;
    }


    public PbMessage(int size) {
        this.mode = Mode.DECODE;
        this.size = size;
    }

    public PbMessage(List<PbField> fields) {
        this.mode = Mode.ENCODE;
        this.fields.clear();
        for (PbField f : fields) {
            this.addField( f );
        }
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
    public int write(byte[] data, int offset) {
        int sum = 0;
        int write;
        for (PbField f : this.fields) {
            write = f.write(data, offset + sum);
            sum += write;
        }
        return sum;
    }

    @Override
    public int write(OutputStream os) throws IOException {
        int sum = 0;
        int write;
        for (PbField f : this.fields) {
            write = f.write(os);
            sum += write;
        }
        return sum;
    }

    @Override
    public int write(ByteBuffer buffer) {
        int sum = 0;
        int write;
        for (PbField f : this.fields) {
            write = f.write(buffer);
            sum += write;
        }
        return sum;
    }

    @Override
    public int read(byte[] data, int offset) {
        int sum = 0;
        int read;
        while (sum < this.size) {
            PbField f = new PbField();
            read = f.read(data, offset + sum);
            this.fields.add(f);
            sum += read;
        }
        return sum;
    }

    @Override
    public int read(InputStream is) throws IOException {
        int sum = 0;
        int read;
        while (is.available() > 0) {
            PbField f = new PbField();
            read = f.read(is);
            this.fields.add(f);
            sum += read;
        }
        this.size = sum;
        return sum;
    }

    @Override
    public int read(ByteBuffer buffer) {
        int sum = 0;
        int read;
        while (buffer.hasRemaining()) {
            PbField f = new PbField();
            read = f.read(buffer);
            this.fields.add(f);
            sum += read;
        }
        this.size = sum;
        return sum;
    }
}