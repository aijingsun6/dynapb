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

    public static class AA {

        @PbSerializedName(fieldNum = 1)
        private List<Long> lv;
        @PbSerializedName(fieldNum = 2)
        private List<Float> fv;
        @PbSerializedName(fieldNum = 3)
        private List<Double> dv;
        @PbSerializedName(fieldNum = 4)
        private List<Boolean> bv;
        @PbSerializedName(fieldNum = 5)
        private List<String> sv;

        public List<Long> getLv() {
            return lv;
        }

        public void setLv(List<Long> lv) {
            this.lv = lv;
        }

        public List<Float> getFv() {
            return fv;
        }

        public void setFv(List<Float> fv) {
            this.fv = fv;
        }

        public List<Double> getDv() {
            return dv;
        }

        public void setDv(List<Double> dv) {
            this.dv = dv;
        }

        public List<Boolean> getBv() {
            return bv;
        }

        public void setBv(List<Boolean> bv) {
            this.bv = bv;
        }

        public List<String> getSv() {
            return sv;
        }

        public void setSv(List<String> sv) {
            this.sv = sv;
        }

        public AA() {
        }
    }

    /**
     * <pre>
     *     decode bytes 28, times 1000000, use 1168 ms
     *     decode bytes 28, times 1000000, use 1213 ms
     *     decode bytes 28, times 1000000, use 1172 ms
     * </pre>
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
        for (int i = 0; i < times; i++) {
            DynaPb.decode(data, 0, data.length, A.class);
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("decode bytes %d, times %d, use %d ms", data.length, times, (end - start)));
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
        A a = DynaPb.decode(data, 0, data.length, A.class);
        Assert.assertEquals(originL, a.getLv());
        Assert.assertEquals(originF, a.getFv(), 0.00001f);
        Assert.assertEquals(originD, a.getDv(), 0.00001f);
        Assert.assertEquals(originB, a.getBv());
        Assert.assertEquals(originS, a.getSv());

        byte[] data2 = DynaPb.encode(a);
        Assert.assertArrayEquals(data, data2);
    }

    @Test
    public void decodeTest2() throws InstantiationException, IllegalAccessException {
        Long[] al = {100L, 200L, 300L, 400L, 500L};
        Float[] fl = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f};
        Double[] dl = {1.0d, 2.0d, 3.0d, 4.0d, 5.0d};
        Boolean[] bl = {true, false, true, false, true};
        String[] sl = {"one", "two", "three", "four", "five"};
        FooBar.Bar.Builder builder = FooBar.Bar.newBuilder();
        for (Long v : al) {
            builder.addLv(v);
        }
        for (Float v : fl) {
            builder.addFv(v);
        }
        for (Double v : dl) {
            builder.addDv(v);
        }
        for (Boolean v : bl) {
            builder.addBv(v);
        }
        for (String v : sl) {
            builder.addSv(v);
        }
        byte[] data = builder.build().toByteArray();
        System.out.println(String.format("encode length %d", data.length));
        AA aa = DynaPb.decode(data, 0, data.length, AA.class);

        for (int i = 0; i < al.length; i++) {
            Assert.assertEquals(al[i], aa.getLv().get(i));
        }
        for (int i = 0; i < fl.length; i++) {
            Assert.assertEquals(fl[i], aa.getFv().get(i), 0.00001f);
        }
        for (int i = 0; i < dl.length; i++) {
            Assert.assertEquals(dl[i], aa.getDv().get(i), 0.00001f);
        }
        for (int i = 0; i < bl.length; i++) {
            Assert.assertEquals(bl[i], aa.getBv().get(i));
        }
        for (int i = 0; i < sl.length;i++){
            Assert.assertEquals(sl[i], aa.getSv().get(i));
        }
        byte[] data2 = DynaPb.encode(aa);
        Assert.assertArrayEquals(data, data2);
        System.out.println("encode and decode list pass");
    }
}
