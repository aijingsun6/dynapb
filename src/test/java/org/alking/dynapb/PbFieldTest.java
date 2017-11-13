package org.alking.dynapb;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Assert;
import org.junit.Test;

public class PbFieldTest {

    @Test
    public void readIntFieldTest(){
        long origin = 100;
        DynaPbTest.Foo.Builder builder = DynaPbTest.Foo.newBuilder();
        builder.setVarInt(origin);
        DynaPbTest.Foo foo = builder.build();
        byte[] data = foo.toByteArray();

        PbField field = new PbField();
        field.read(data,0, data.length);
        int fieldNum = field.getFieldNum();
        WireType wireType = field.wireType();
        PbValue value = (PbValue) field.getValue();
        Assert.assertEquals( 1, fieldNum);
        Assert.assertEquals( WireType.VARINT, wireType);
        Assert.assertEquals(origin, value.longValue());
    }

    @Test
    public void writeIntFieldTest() throws InvalidProtocolBufferException {
        long origin = 100;
        PbField field = new PbField(1, PbValue.valueOf(origin));
        int size = field.size();
        byte[] data = new byte[size];
        field.write(data,0);
        DynaPbTest.Foo foo = DynaPbTest.Foo.parseFrom(data);
        Assert.assertEquals(origin, foo.getVarInt());
    }


}