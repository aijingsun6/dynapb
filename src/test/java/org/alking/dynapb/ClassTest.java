package org.alking.dynapb;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClassTest {

    public static class Bar {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Bar() {
        }
    }

    public static class Foo {

        private Boolean bv;

        private Integer iv;

        private Long lv;

        private Float fv;

        private Double dv;

        private String sv;

        public Boolean getBv() {
            return bv;
        }

        public void setBv(Boolean bv) {
            this.bv = bv;
        }

        public Integer getIv() {
            return iv;
        }

        public void setIv(Integer iv) {
            this.iv = iv;
        }

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

        public String getSv() {
            return sv;
        }

        public void setSv(String sv) {
            this.sv = sv;
        }

        public Foo() {
        }
    }

    public static class Foo2 {
        private boolean bv;
        private int iv;
        private long lv;
        private float fv;
        private double dv;

        public boolean isBv() {
            return bv;
        }

        public void setBv(boolean bv) {
            this.bv = bv;
        }

        public int getIv() {
            return iv;
        }

        public void setIv(int iv) {
            this.iv = iv;
        }

        public long getLv() {
            return lv;
        }

        public void setLv(long lv) {
            this.lv = lv;
        }

        public float getFv() {
            return fv;
        }

        public void setFv(float fv) {
            this.fv = fv;
        }

        public double getDv() {
            return dv;
        }

        public void setDv(double dv) {
            this.dv = dv;
        }

        public Foo2() {
        }
    }

    public static class Foo3 {

        private List<Boolean> bl = new ArrayList<>();
        private List<Integer> il = new ArrayList<>();
        private List<Long> ll = new ArrayList<>();
        private List<Float> fl = new ArrayList<>();
        private List<Double> dl = new ArrayList<>();
        private List<String> sl = new ArrayList<>();

        public List<Boolean> getBl() {
            return bl;
        }

        public void setBl(List<Boolean> bl) {
            this.bl = bl;
        }

        public List<Integer> getIl() {
            return il;
        }

        public void setIl(List<Integer> il) {
            this.il = il;
        }

        public List<Long> getLl() {
            return ll;
        }

        public void setLl(List<Long> ll) {
            this.ll = ll;
        }

        public List<Float> getFl() {
            return fl;
        }

        public void setFl(List<Float> fl) {
            this.fl = fl;
        }

        public List<Double> getDl() {
            return dl;
        }

        public void setDl(List<Double> dl) {
            this.dl = dl;
        }

        public List<String> getSl() {
            return sl;
        }

        public void setSl(List<String> sl) {
            this.sl = sl;
        }

        public Foo3() {
        }
    }

    public static class Foo4 {

        private Bar bar;
        private List<Bar> bl = new ArrayList<>();

        public Bar getBar() {
            return bar;
        }

        public void setBar(Bar bar) {
            this.bar = bar;
        }

        public List<Bar> getBl() {
            return bl;
        }

        public void setBl(List<Bar> bl) {
            this.bl = bl;
        }

        public Foo4() {
        }
    }

    public static class Foo5 {
        private byte[] byteArr;

        public byte[] getByteArr() {
            return byteArr;
        }

        public void setByteArr(byte[] byteArr) {
            this.byteArr = byteArr;
        }

        public Foo5() {
        }
    }

    @Test
    public void testFoo(){
        Class clazz = Foo.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields){
            String name = f.getName();
            Type t = f.getGenericType();
            if("bv".equals(name)){
                Assert.assertTrue(Boolean.class.equals(t));
            }
            if("iv".equals(name)){
                Assert.assertTrue(Integer.class.equals(t));
            }
            if("lv".equals(name)){
                Assert.assertTrue(Long.class.equals(t));
            }
            if("fv".equals(name)){
                Assert.assertTrue(Float.class.equals(t));
            }
            if("dv".equals(name)){
                Assert.assertTrue(Double.class.equals(t));
            }
            if("sv".equals(name)){
                Assert.assertTrue(String.class.equals(t));
            }
        }

    }

    @Test
    public void testFoo2(){
        Class clazz = Foo2.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields){
            String name = f.getName();
            Type t = f.getGenericType();
            if("bv".equals(name)){
                Assert.assertTrue(boolean.class.equals(t));
            }
            if("iv".equals(name)){
                Assert.assertTrue(int.class.equals(t));
            }
            if("lv".equals(name)){
                Assert.assertTrue(long.class.equals(t));
            }
            if("fv".equals(name)){
                Assert.assertTrue(float.class.equals(t));
            }
            if("dv".equals(name)){
                Assert.assertTrue(double.class.equals(t));
            }
        }
    }

    @Test
    public void testFoo3(){
        Class clazz = Foo3.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields){
            String name = f.getName();
            Class c = f.getType();
            Assert.assertEquals(List.class, c);
            ParameterizedType t = (ParameterizedType)f.getGenericType();
            Type ac = t.getActualTypeArguments()[0];
            if("bl".equals(name)){
                Assert.assertEquals(Boolean.class, ac);
            }
            if("il".equals(name)){
                Assert.assertEquals(Integer.class, ac);
            }
            if("ll".equals(name)){
                Assert.assertEquals(Long.class, ac);
            }
            if("fl".equals(name)){
                Assert.assertEquals(Float.class, ac);
            }
            if("dl".equals(name)){
                Assert.assertEquals(Double.class, ac);
            }
            if("sl".equals(name)){
                Assert.assertEquals(String.class, ac);
            }
        }

    }

    @Test
    public void testFoo4(){
        Class clazz = Foo4.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields){
            String name = f.getName();
            Class t = f.getType();
            if("bar".equals(name)){
                Assert.assertEquals(Bar.class, t);
            }
            if("bl".equals(name)){
                Assert.assertEquals(List.class, t);
                ParameterizedType t2 = (ParameterizedType)f.getGenericType();
                Type ac = t2.getActualTypeArguments()[0];
                Assert.assertEquals(Bar.class, ac);
            }
        }
    }

    @Test
    public void testFoo5(){
        Class clazz = Foo5.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields){
            String name = f.getName();
            Class t = f.getType();
            if("byteArr".equals(name)){
                Assert.assertEquals(byte[].class, t);
            }

        }
    }

    @Test
    public void barTest() throws IllegalAccessException, InstantiationException {
        Class clazz = Bar.class;
        int id = 1000;
        Object obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields){
            f.setAccessible(true);
            if(f.getName().equals("id")){
                f.set(obj,id);
            }
        }
        Bar bar = (Bar)obj;
        Assert.assertEquals(id, bar.getId());
        Integer id2 = 100;
        for (Field f: fields){
            f.setAccessible(true);
            if(f.getName().equals("id")){
                f.set(obj,id2);
            }
        }

    }


}