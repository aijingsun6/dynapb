package org.alking.dynapb;

import org.junit.Assert;
import org.junit.Test;

public class PbBytesTest {

    @Test
    public void readTest(){
        String origin = "i am ok";
        FooBar.Foo.Builder builder = FooBar.Foo.newBuilder();
        builder.setVarString(origin);

        FooBar.Foo foo = builder.build();
        byte[] bytes = foo.toByteArray();
        PbBytes pb = new PbBytes();
        pb.read(bytes,2, bytes.length-2);
        String find = pb.stringValue();
        Assert.assertEquals(origin, find);
    }

    @Test
    public void writeTest(){
        String origin = "i am ok";
        FooBar.Foo.Builder builder = FooBar.Foo.newBuilder();
        builder.setVarString(origin);

        FooBar.Foo foo = builder.build();
        byte[] bytes = foo.toByteArray();
        byte[] bytes2 = new byte[bytes.length];
        // 0 : field num and wire type
        // 2 : length
        bytes2[0] = bytes[0];
        bytes2[1] = bytes[1];
        PbBytes pb = new PbBytes(origin);
        pb.write(bytes2, 2);
        Assert.assertArrayEquals(bytes, bytes2);
    }
}