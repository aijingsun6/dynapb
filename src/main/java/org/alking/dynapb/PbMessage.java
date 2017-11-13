package org.alking.dynapb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * for java class
 */
final class PbMessage implements PbRW {

    private List<PbField> fields = new ArrayList<>();

    public List<PbField> getFields() {
        return fields;
    }

    public void addField(PbField field) {
        this.fields.add(field);
    }

    public List<PbField> getField(int fieldNum){
        List<PbField> result = new ArrayList<>();
        for(PbField f: this.fields){
            if(f.getFieldNum() == fieldNum){
                result.add(f);
            }
        }
        return result;
    }

    public PbMessage() {
    }

    public PbMessage(List<PbField> fields) {
        this.fields.clear();
        for (PbField f : fields) {
            this.fields.add(f);
        }
    }


    @Override
    public WireType wireType() {
        return WireType.BYTES;
    }

    @Override
    public int size() {
        int sum = 0;
        for (PbField f : this.fields) {
            sum += f.size();
        }
        return sum;
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
    public int read(byte[] data, int offset, int limit) {
        int sum = 0;
        int read;
        while (sum < limit) {
            PbField f = new PbField();
            read = f.read(data, offset + sum, limit - sum);
            this.addField(f);
            sum += read;
        }
        return sum;
    }

    @Override
    public int read(InputStream is, int limit) throws IOException {
        int sum = 0;
        int read;
        while (is.available() > 0) {
            PbField f = new PbField();
            read = f.read(is, 0);
            this.addField(f);
            sum += read;
        }
        return sum;
    }

    @Override
    public int read(ByteBuffer buffer, int limit) {
        int sum = 0;
        int read;
        while (buffer.hasRemaining()) {
            PbField f = new PbField();
            read = f.read(buffer, 0);
            this.addField(f);
            sum += read;
        }
        return sum;
    }
}