package org.alking.dynapb;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Assert;
import org.junit.Test;

public class PbFieldTest {

    @Test
    public void readIntFieldTest(){
        long origin = 100;
        FooBar.Foo.Builder builder = FooBar.Foo.newBuilder();
        builder.setVarInt(origin);
        FooBar.Foo foo = builder.build();
        byte[] data = foo.toByteArray();

        PbField field = new PbField();
        field.read(data,0);
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
        FooBar.Foo foo = FooBar.Foo.parseFrom(data);
        Assert.assertEquals(origin, foo.getVarInt());
    }


}