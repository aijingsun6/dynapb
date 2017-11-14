package org.alking.dynapb;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

interface PbWriter {

    /**
     * write to bytes
     * @param data
     * @param offset
     * @return data size in bytes
     */
    int write(final byte[] data, final int offset);

    /**
     * write to {@link OutputStream}
     * @param os {@link OutputStream}
     * @return
     * @throws IOException
     */
    int write(final OutputStream os) throws IOException;

    /**
     *  write to {@link ByteBuffer}
     * @param buffer {@link ByteBuffer}
     * @return
     */
    int write(final ByteBuffer buffer);
}