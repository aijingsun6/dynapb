package org.alking.dynapb;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PbVarIntTest {

    @Test
    public void readTest(){
        List<Long> list = new ArrayList<>();
        list.add(-2L);
        list.add(-1L);
        list.add(0L);
        list.add(1L);
        list.add(2L);
        list.add(Long.MAX_VALUE);
        list.add(Long.MAX_VALUE + 1);

        for (long v : list){
            DynaPb.Foo.Builder builder = DynaPb.Foo.newBuilder();
            builder.setVarInt(v);

            DynaPb.Foo foo = builder.build();
            byte[]  bytes = foo.toByteArray();
            PbVarInt pbVarInt = new PbVarInt();
            pbVarInt.read(bytes, 1);
            Assert.assertEquals(v, pbVarInt.getValue());
        }
    }

    @Test
    public void writeTest(){
        List<Long> list = new ArrayList<>();

        list.add(0L);
        list.add(1L);
        list.add(2L);
        list.add(Long.MAX_VALUE);

        list.add(-1L);
        list.add(-2L);
        list.add(Long.MIN_VALUE);
        for (long v : list){
            DynaPb.Foo.Builder builder = DynaPb.Foo.newBuilder();
            builder.setVarInt(v);

            DynaPb.Foo foo = builder.build();
            byte[] bytes = foo.toByteArray();
            byte[] bytes2 = new byte[bytes.length];
            bytes2[0] = bytes[0];
            PbVarInt pbVarInt  = new PbVarInt(v);
            pbVarInt.write(bytes2, 1);
            System.out.println(String.format("write %d ......", v));
            Assert.assertArrayEquals( bytes, bytes2);
            System.out.println(String.format("write %d passed", v));

        }
    }
}