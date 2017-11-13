package org.alking.dynapb;

import org.junit.Assert;
import org.junit.Test;


public class PbBit32Test {

    @Test
    public void readTest(){
        float origin = 1.6f;
        DynaPbTest.Foo.Builder builder = DynaPbTest.Foo.newBuilder();
        builder.setVarFloat(origin);

        DynaPbTest.Foo foo = builder.build();
        byte[]  bytes = foo.toByteArray();
        PbBit32 bit32 = new PbBit32();
        bit32.read(bytes, 1, 0);
        Assert.assertEquals(origin, bit32.floatValue(), 0.000001f);
    }

    @Test
    public void writeTest(){
        float origin = 1.6f;
        DynaPbTest.Foo.Builder builder = DynaPbTest.Foo.newBuilder();
        builder.setVarFloat(origin);
        DynaPbTest.Foo foo = builder.build();
        byte[]  bytes = foo.toByteArray();
        byte[]  bytes2 = new byte[bytes.length];
        bytes2[0] = bytes[0];
        PbBit32 bit32 = new PbBit32(origin);
        bit32.write(bytes2, 1);
        Assert.assertArrayEquals( bytes, bytes2 );
    }
}