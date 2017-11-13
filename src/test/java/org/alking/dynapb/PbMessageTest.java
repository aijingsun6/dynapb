package org.alking.dynapb;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PbMessageTest {

    @Test
    public void readTest(){
        long lv = 100;
        float fv = 1.0f;
        double dv = 3.0d;
        boolean bv = true;
        String sv = "i am ok";
        FooBar.Foo.Builder builder = FooBar.Foo.newBuilder();
        builder.setVarInt(lv);
        builder.setVarFloat(fv);
        builder.setVarDouble(dv);
        builder.setVarBool(bv);
        builder.setVarString(sv);
        FooBar.Foo foo = builder.build();
        byte[] data = foo.toByteArray();

        PbMessage msg = new PbMessage();
        msg.read(data,0, data.length);

        List<PbField> sf = msg.getField(5);
        Assert.assertTrue(sf.size() == 1);
        // check i am ok string
        PbValue sfv = (PbValue) sf.get(0).getValue();
        Assert.assertEquals(sv, sfv.stringValue());
    }

    @Test
    public void writeTest() throws InvalidProtocolBufferException {
        long lv = 100;
        float fv = 1.0f;
        double dv = 3.0d;
        boolean bv = true;
        String sv = "i am ok";
        PbMessage msg = new PbMessage();
        msg.addField( new PbField(1,PbVarInt.valueOf(lv)) );
        msg.addField( new PbField(2,PbVarInt.valueOf(fv)) );
        msg.addField( new PbField(3,PbVarInt.valueOf(dv)) );
        msg.addField( new PbField(4,PbVarInt.valueOf(bv)) );
        msg.addField( new PbField(5,PbVarInt.valueOf(sv)) );
        int size = msg.size();
        byte[] data = new byte[size];
        msg.write(data, 0);
        FooBar.Foo foo = FooBar.Foo.parseFrom(data);
        Assert.assertEquals(lv, foo.getVarInt());
        Assert.assertEquals(fv, foo.getVarFloat(), 0.000001f);
        Assert.assertEquals(dv, foo.getVarDouble(), 0.000001f);
        Assert.assertTrue(foo.getVarBool());
        Assert.assertEquals(sv, foo.getVarString());
    }
}