package org.alking.dynapb;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DynaPbTest {

    public static class A {

        @PbSerializedName(fieldNum = 1)
        private Long lv;
        @PbSerializedName(fieldNum = 2)
        private Float fv;
        @PbSerializedName(fieldNum = 3)
        private Double dv;
        @PbSerializedName(fieldNum = 4)
        private Boolean bv;
        @PbSerializedName(fieldNum = 5)
        private String sv;

        public Long getLv() {
            return lv;
        }

        public void setLv(Long lv) {
            this.lv = lv;
        }

        public Float getFv() {
            return fv;
        }

        public void setFv(Float fv) {
            this.fv = fv;
        }

        public Double getDv() {
            return dv;
        }

        public void setDv(Double dv) {
            this.dv = dv;
        }

        public Boolean getBv() {
            return bv;
        }

        public void setBv(Boolean bv) {
            this.bv = bv;
        }

        public String getSv() {
            return sv;
        }

        public void setSv(String sv) {
            this.sv = sv;
        }

        public A() {
        }
    }

    public static class DBar {

        @PbSerializedName(fieldNum = 1)
        private Integer lv;

        @PbSerializedName(fieldNum = 2)
        private List<Integer> ll = new ArrayList<>();

        public Integer getLv() {
            return lv;
        }

        public void setLv(Integer lv) {
            this.lv = lv;
        }

        public List<Integer> getLl() {
            return ll;
        }

        public void setLl(List<Integer> ll) {
            this.ll = ll;
        }

        public DBar() {
        }
    }

    /**
     * <pre>
     *     decode bytes 28, times 1000000, use 1168 ms
     *     decode bytes 28, times 1000000, use 1213 ms
     *     decode bytes 28, times 1000000, use 1172 ms
     * </pre>
     *
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void performTest() throws InstantiationException, IllegalAccessException {
        Long originL = 1000L;
        Float originF = 1.0f;
        Double originD = 4.0d;
        Boolean originB = true;
        String originS = "i am ok";


        FooBar.Foo.Builder builder = FooBar.Foo.newBuilder();
        builder.setVarInt(originL);
        builder.setVarFloat(originF);
        builder.setVarDouble(originD);
        builder.setVarBool(originB);
        builder.setVarString(originS);

        FooBar.Foo foo = builder.build();
        byte[] data = foo.toByteArray();
        long start = System.currentTimeMillis();
        int times = 100 * 10000;
        for (int i = 0 ; i < times; i++){
            DynaPb.decode(data,0, data.length,A.class);
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("decode bytes %d, times %d, use %d ms",data.length, times, (end - start)));
    }

    @Test
    public void decodeTest1() throws InstantiationException, IllegalAccessException {
        Long originL = 1000L;
        Float originF = 1.0f;
        Double originD = 4.0d;
        Boolean originB = true;
        String originS = "i am ok";


        FooBar.Foo.Builder builder = FooBar.Foo.newBuilder();
        builder.setVarInt(originL);
        builder.setVarFloat(originF);
        builder.setVarDouble(originD);
        builder.setVarBool(originB);
        builder.setVarString(originS);

        FooBar.Foo foo = builder.build();
        byte[] data = foo.toByteArray();
        A a = DynaPb.decode(data,0, data.length,A.class);
        Assert.assertEquals(originL, a.getLv());
        Assert.assertEquals(originF, a.getFv(),0.00001f);
        Assert.assertEquals(originD, a.getDv(), 0.00001f);
        Assert.assertEquals(originB, a.getBv());
        Assert.assertEquals(originS, a.getSv());

        byte[] data2 = DynaPb.encode(a);
        Assert.assertArrayEquals(data, data2);
    }

    @Test
    public void decodeTest2() throws InstantiationException, IllegalAccessException {
        int i1 = 100;
        int i2 = 200;
        int i3 = 300;
        int i4 = 400;

        FooBar.Bar.Builder builder = FooBar.Bar.newBuilder();
        builder.setId(i1);
        builder.addIntList(i2);
        builder.addIntList(i3);
        builder.addIntList(i4);
        byte[] data = builder.build().toByteArray();
        DBar dbar = DynaPb.decode(data,0, data.length, DBar.class);
        Assert.assertEquals(new Integer(i1), dbar.getLv());
        Assert.assertEquals(3 ,dbar.getLl().size());
        Assert.assertEquals(new Integer(i2) ,dbar.getLl().get(0));
        Assert.assertEquals(new Integer(i3) ,dbar.getLl().get(1));
        Assert.assertEquals(new Integer(i4) ,dbar.getLl().get(2));

        byte[] data2 = DynaPb.encode(dbar);
        Assert.assertArrayEquals(data, data2);
    }
}
