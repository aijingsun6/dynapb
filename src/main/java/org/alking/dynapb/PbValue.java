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

    int read(final byte[] data, final int offset, final int limit);

    int write(final byte[] data, final int offset);

    int read(final InputStream is, final int limit) throws IOException;

    int write(final OutputStream os) throws IOException;

    int read(final ByteBuffer buffer, final int limit);

    int write(final ByteBuffer buffer);
}