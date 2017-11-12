package org.alking.dynapb;

public enum WireType {
    /**
     * for {@link Integer}, {@link Long}, {@link Boolean}
     */
    VARINT(0),
    /**
     * for {@link Double}
     */
    BIT64(1),
    /**
     * for {@link String}, byte[], message
     */
    BYTES(2),
    /**
     * for {@link Float}
     */
    BIT32(5);
    private int code;

    public int getCode() {
        return code;
    }

    private WireType(int code) {
        this.code = code;
    }
}