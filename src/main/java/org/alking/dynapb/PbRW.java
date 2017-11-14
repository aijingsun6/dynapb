package org.alking.dynapb;

interface PbRW extends PbReader, PbWriter{

    WireType wireType();

    int size();

}