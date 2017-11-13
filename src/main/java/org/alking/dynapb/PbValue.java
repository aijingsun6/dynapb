package org.alking.dynapb;

interface PbValue extends PbReader, PbWriter {

    WireType type();

    boolean boolValue();

    int intValue();

    long longValue();

    float floatValue();

    double doubleValue();

    String stringValue();

}