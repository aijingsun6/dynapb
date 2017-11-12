package org.alking.dynapb;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

interface PbValue {

    int size();

    void read(byte[] data, int offset);
    void write(byte[] data, int offset);

    void read(InputStream is);
    void write(OutputStream os);

    void read(ByteBuffer buffer);
    void write(ByteBuffer buffer);
}