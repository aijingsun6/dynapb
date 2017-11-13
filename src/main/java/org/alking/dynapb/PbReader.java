package org.alking.dynapb;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

interface PbReader {

    /**
     * data size
     * @return size in bytes
     */
    int size();

    /**
     * read from bytes
     * @param data
     * @param offset
     * @param limit
     * @return data size in bytes
     */
    int read(final byte[] data, final int offset, final int limit);

    /**
     * read from {@link InputStream}
     * @param is
     * @param limit
     * @return data size in bytes
     * @throws IOException
     */
    int read(final InputStream is, final int limit) throws IOException;

    /**
     * read from {@link ByteBuffer}
     * @param buffer
     * @param limit
     * @return data size in bytes
     */
    int read(final ByteBuffer buffer, final int limit);
}