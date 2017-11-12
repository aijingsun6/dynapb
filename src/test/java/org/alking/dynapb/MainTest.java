package org.alking.dynapb;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    @Test
    public void floatTest(){
        DynaPb.Foo.Builder builder = DynaPb.Foo.newBuilder();
        builder.setVarFloat(1.0f);
        DynaPb.Foo foo = builder.build();
        byte[]  bytes = foo.toByteArray();
        System.out.println(bytes.length);
        ByteBuffer bb = ByteBuffer.wrap(bytes,1,4);
        bb.order( ByteOrder.LITTLE_ENDIAN );
        float f = bb.getFloat();
        System.out.println(f);
    }

    @Test
    public void doubleTest(){
        DynaPb.Foo.Builder builder = DynaPb.Foo.newBuilder();
        builder.setVarDouble(2.0d);
        DynaPb.Foo foo = builder.build();
        byte[]  bytes = foo.toByteArray();
        System.out.println(bytes.length);
        ByteBuffer bb = ByteBuffer.wrap(bytes,1,8);
        bb.order( ByteOrder.LITTLE_ENDIAN );
        Double d = bb.getDouble();
        System.out.println(d);
    }
}