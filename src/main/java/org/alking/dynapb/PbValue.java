package org.alking.dynapb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

interface PbValue {

    int size();

    WireType type();

    boolean boolValue();

    int intValue();

    long longValue();

    float floatValue();

    double doubleValue();

    String stringValue();

    int read(byte[] data, int offset);

    int write(byte[] data, int offset);

    int read(InputStream is) throws IOException;

    int write(OutputStream os) throws IOException;

    int read(ByteBuffer buffer);

    int write(ByteBuffer buffer);
}