package org.alking.dynapb;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

interface PbReader {

    /**
     * read from bytes
     * @param data byte[]
     * @param offset offset
     * @return data size in bytes
     */
    int read(final byte[] data, final int offset);

    /**
     * read from {@link InputStream}
     * @param is {@link InputStream}
     * @return data size in bytes
     * @throws IOException
     */
    int read(final InputStream is) throws IOException;

    /**
     * read from {@link ByteBuffer}
     * @param buffer {@link ByteBuffer}
     * @return data size in bytes
     */
    int read(final ByteBuffer buffer);
}