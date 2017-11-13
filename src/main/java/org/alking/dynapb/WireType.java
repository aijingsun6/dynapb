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
    public static WireType valueOf(int code){
        switch (code){
            case 0:
                return VARINT;
            case 1:
                return BIT64;
            case 2:
                return BYTES;
            case 5:
                return BIT32;
        }
        throw new PbException(String.format("unknown code %d",code));
    }
}