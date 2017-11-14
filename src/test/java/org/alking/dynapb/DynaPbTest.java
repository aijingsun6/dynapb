package org.alking.dynapb;

import org.junit.Assert;
import org.junit.Test;

public class DynaPbTest {

    public static class A {
        @PbSerializedName(fieldNum = 1)
        private Long lv;

        public Long getLv() {
            return lv;
        }

        public void setLv(Long lv) {
            this.lv = lv;
        }

        public A() {
        }
    }

    @Test
    public void decodeTest1() throws InstantiationException, IllegalAccessException {
        Long origin = 1000L;
        FooBar.Foo.Builder builder = FooBar.Foo.newBuilder();
        builder.setVarInt(origin);
        FooBar.Foo foo = builder.build();
        byte[] data = foo.toByteArray();
        A a = DynaPb.decode(data,0, data.length,A.class);
        Assert.assertEquals(origin, a.getLv());
    }
}
