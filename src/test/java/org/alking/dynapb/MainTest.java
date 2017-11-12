package org.alking.dynapb;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void test(){
        Assert.assertTrue(true);
    }
    @Test
    public void writeTest(){
        DynaPb.Foo.Builder builder = DynaPb.Foo.newBuilder();
        builder.setVarInt(1);

        DynaPb.Foo foo = builder.build();
        byte[]  bytes = foo.toByteArray();



    }
}