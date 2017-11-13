package org.alking.dynapb;

import org.junit.Assert;
import org.junit.Test;

public class PbBit64Test {

    @Test
    public void readTest(){
        double origin = 1.6d;
        FooBar.Foo.Builder builder = FooBar.Foo.newBuilder();
        builder.setVarDouble(origin);

        FooBar.Foo foo = builder.build();
        byte[]  bytes = foo.toByteArray();
        PbBit64 bit64 = new PbBit64();
        bit64.read(bytes, 1, 0);
        Assert.assertEquals(origin, bit64.doubleValue(), 0.000001f);
    }

    @Test
    public void writeTest(){
        double origin = 1.6d;
        FooBar.Foo.Builder builder = FooBar.Foo.newBuilder();
        builder.setVarDouble(origin);

        FooBar.Foo foo = builder.build();
        byte[] bytes = foo.toByteArray();
        byte[] bytes2 = new byte[bytes.length];
        bytes2[0] = bytes[0];
        PbBit64 bit64 = new PbBit64(origin);
        bit64.write(bytes2, 1);
        Assert.assertArrayEquals(bytes, bytes2);

    }
}