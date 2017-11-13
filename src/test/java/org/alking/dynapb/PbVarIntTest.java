package org.alking.dynapb;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PbVarIntTest {

    private List<Long> genList(){
        List<Long> list = new ArrayList<>();
        list.add(0L);

        for (long v = 1; v < 10000; v +=100){
            list.add(v);
        }

        list.add(Long.MAX_VALUE);

        for (long v = -1; v > -10000; v-=100){
            list.add(v);
        }
        list.add(Long.MIN_VALUE);
        return list;
    }

    @Test
    public void readTest(){
        List<Long> list = this.genList();

        for (long v : list){
            DynaPb.Foo.Builder builder = DynaPb.Foo.newBuilder();
            builder.setVarInt(v);

            DynaPb.Foo foo = builder.build();
            byte[]  bytes = foo.toByteArray();
            PbVarInt pbVarInt = new PbVarInt();
            pbVarInt.read(bytes, 1, 0);
            Assert.assertEquals(v, pbVarInt.longValue());
            System.out.println(String.format("read %d passed", v));
        }
    }

    @Test
    public void writeTest(){
        List<Long> list = this.genList();
        for (long v : list){
            DynaPb.Foo.Builder builder = DynaPb.Foo.newBuilder();
            builder.setVarInt(v);

            DynaPb.Foo foo = builder.build();
            byte[] bytes = foo.toByteArray();
            byte[] bytes2 = new byte[bytes.length];
            bytes2[0] = bytes[0];
            PbVarInt pbVarInt  = new PbVarInt(v);
            pbVarInt.write(bytes2, 1);
            Assert.assertArrayEquals( bytes, bytes2);
            System.out.println(String.format("write %d passed", v));

        }
    }

    @Test
    public void boolReadTest(){
        DynaPb.Foo.Builder builder = DynaPb.Foo.newBuilder();
        builder.setVarBool(true);
        DynaPb.Foo foo = builder.build();
        byte[]  bytes = foo.toByteArray();
        PbVarInt pbVarInt = new PbVarInt();
        pbVarInt.read(bytes, 1, 0);
        Assert.assertTrue(pbVarInt.boolValue());

        builder = DynaPb.Foo.newBuilder();
        builder.setVarBool(false);
        foo = builder.build();
        bytes = foo.toByteArray();
        pbVarInt = new PbVarInt();
        pbVarInt.read(bytes, 1, 0);
        Assert.assertFalse(pbVarInt.boolValue());
    }

    @Test
    public void boolWriteTest(){
        DynaPb.Foo.Builder builder = DynaPb.Foo.newBuilder();
        builder.setVarBool(true);
        DynaPb.Foo foo = builder.build();
        byte[] bytes = foo.toByteArray();
        byte[] bytes2 = new byte[bytes.length];
        bytes2[0] = bytes[0];
        PbVarInt pbVarInt  = new PbVarInt(true);
        pbVarInt.write(bytes2, 1);
        Assert.assertArrayEquals( bytes, bytes2);

        builder = DynaPb.Foo.newBuilder();
        builder.setVarBool(false);
        foo = builder.build();
        bytes = foo.toByteArray();
        bytes2 = new byte[bytes.length];
        bytes2[0] = bytes[0];
        pbVarInt  = new PbVarInt(false);
        pbVarInt.write(bytes2, 1);
        Assert.assertArrayEquals( bytes, bytes2);
    }


}