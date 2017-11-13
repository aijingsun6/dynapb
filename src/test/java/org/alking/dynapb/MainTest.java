package org.alking.dynapb;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class MainTest {

    @Test
    public void floatTest(){
        DynaPbTest.Foo.Builder builder = DynaPbTest.Foo.newBuilder();
        float origin = 1.0f;
        builder.setVarFloat(origin);

        DynaPbTest.Foo foo = builder.build();
        byte[]  bytes = foo.toByteArray();
        System.out.println(bytes.length);
        ByteBuffer bb = ByteBuffer.wrap(bytes,1,4);
        bb.order( ByteOrder.LITTLE_ENDIAN );
        float find = bb.getFloat();
        Assert.assertEquals(origin, find, 0.000001f);
    }

    @Test
    public void doubleTest(){
        DynaPbTest.Foo.Builder builder = DynaPbTest.Foo.newBuilder();
        double origin = 2.0d;
        builder.setVarDouble(origin);
        DynaPbTest.Foo foo = builder.build();
        byte[]  bytes = foo.toByteArray();
        System.out.println(bytes.length);
        ByteBuffer bb = ByteBuffer.wrap(bytes,1,8);
        bb.order( ByteOrder.LITTLE_ENDIAN );
        double d = bb.getDouble();
        Assert.assertEquals(origin, d, 0.000001f);
    }
}