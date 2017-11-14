package org.alking.dynapb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>support field type: </p>
 * <ul>
 * <li>{@link Boolean} or boolean </li>
 * <li>{@link Integer} or int </li>
 * <li>{@link Long} or long </li>
 * <li>{@link Float} or float </li>
 * <li>{@link Double} or double </li>
 * <li>{@link String}</li>
 * <li>byte[]</li>
 * <li> CustomClass </li>
 * <li>{@link List}&lt;{@link Boolean}&gt; </li>
 * <li>{@link List}&lt;{@link Integer}&gt; </li>
 * <li>{@link List}&lt;{@link Long}&gt; </li>
 * <li>{@link List}&lt;{@link Float}&gt; </li>
 * <li>{@link List}&lt;{@link Double}&gt; </li>
 * <li>{@link List}&lt;{@link String}&gt; </li>
 * <li>{@link List}&lt;byte[]&gt; </li>
 * <li>{@link List}&lt;CustomClass&gt; </li>
 * </ul>
 */
public class DynaPb {

    /**
     * A constant holding the minimum value a {@code short} can
     * have, 2<sup>15</sup>.
     */
    public static final int THREAD_LOCAL_CACHE_MAX = 1 << 15;

    private static final ThreadLocal<byte[]> cache = new ThreadLocal<byte[]>(){
        @Override
        protected byte[] initialValue() {
            return new byte[THREAD_LOCAL_CACHE_MAX];
        }
    };

    public static <T> T decode(final byte[] data, final int offset, final int size, final Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();
        PbMessage msg = new PbMessage(size);
        msg.read(data, offset);
        decode(msg, clazz, t);
        return t;
    }

    public static <T> T decode(final InputStream is, final Class<T> clazz) throws IllegalAccessException, InstantiationException, IOException {
        T t = clazz.newInstance();
        PbMessage msg = new PbMessage(0);
        msg.read(is);
        decode(msg, clazz, t);
        return t;
    }

    public static <T> T decode(final ByteBuffer buffer, final Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();
        PbMessage msg = new PbMessage(0);
        msg.read(buffer);
        decode(msg, clazz, t);
        return t;
    }

    private static void checkDuplicateFields( Field[] fields ){
        Map<Integer, String> map = new HashMap<>();
        for (Field f : fields) {
            String name = f.getName();
            PbSerializedName anno = f.getAnnotation(PbSerializedName.class);
            if (anno == null) {
                continue;
            }
            int fieldNum = anno.fieldNum();
            // check duplicate field number
            if (map.containsKey(fieldNum)) {
                throw new PbException(String.format("field number %d has duplicate field %s and %s", fieldNum, map.get(fieldNum), name));
            }
            map.put(fieldNum, name);
        }
    }
    private static <T> void decode(PbMessage msg, Class<T> clazz, T t) throws IllegalAccessException, InstantiationException {
        Field[] fields = clazz.getDeclaredFields();
        checkDuplicateFields(fields);
        for (Field f : fields) {
            String name = f.getName();
            PbSerializedName anno = f.getAnnotation(PbSerializedName.class);
            if (anno == null) {
                continue;
            }
            int fieldNum = anno.fieldNum();
            decodeField(msg.getFieldByFieldNum(fieldNum), f, t);
        }
    }

    /**
     * <ul>
     * <li>{@link List}&lt;{@link Boolean}&gt; </li>
     * <li>{@link List}&lt;{@link Integer}&gt; </li>
     * <li>{@link List}&lt;{@link Long}&gt; </li>
     * <li>{@link List}&lt;{@link Float}&gt; </li>
     * <li>{@link List}&lt;{@link Double}&gt; </li>
     * <li>{@link List}&lt;{@link String}&gt; </li>
     * <li>{@link List}&lt;byte[]&gt; </li>
     * <li>{@link List}&lt;CustomClass&gt; </li>
     * </ul>
     *
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    private static <T> void decodeList(PbValue value, Field field, T t) throws IllegalAccessException, InstantiationException {
        field.setAccessible(true);
        ParameterizedType pt = (ParameterizedType) field.getGenericType();
        Type at = pt.getActualTypeArguments()[0];

        Object obj = field.get(t);

        // List<Boolean>
        if (Boolean.class.equals(at)) {
            List<Boolean> bl = (List<Boolean>) obj;
            bl.add(value.boolValue());
            return;
        }

        if (Integer.class.equals(at)) {
            List<Integer> il = (List<Integer>) field.get(t);
            il.add(value.intValue());
            return;
        }

        if (Long.class.equals(at)) {
            List<Long> ll = (List<Long>) obj;
            ll.add(value.longValue());
            return;
        }
        if (Float.class.equals(at)) {
            List<Float> fl = (List<Float>) obj;
            fl.add(value.floatValue());
            return;
        }
        if (Double.class.equals(at)) {
            List<Double> dl = (List<Double>) obj;
            dl.add(value.doubleValue());
            return;
        }
        if (String.class.equals(at)) {
            List<String> sl = (List<String>) obj;
            sl.add(value.stringValue());
            return;
        }
        // List<byte[]>
        if (byte[].class.equals(at)) {
            List<byte[]> bsl = (List<byte[]>) obj;
            byte[] data = new byte[value.size()];
            value.write(data, 0);
            bsl.add(data);
            return;
        }
        // List<CustomClass>
        Class<?> c = (Class<?>) at;
        List list = (List) obj;
        Object co = decodeObj(value, c);
        list.add(co);
    }

    private static <T> T decodeObj(PbValue value, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        WireType wt = value.wireType();
        if (!wt.equals(WireType.BYTES)) {
            throw new PbException(String.format("illegal wire type %s", wt));
        }
        PbBytes bytes = (PbBytes) value;
        PbMessage msg = new PbMessage(bytes.size());
        msg.read(bytes.getBytes(), bytes.getOffset());
        T obj = clazz.newInstance();
        decode(msg, clazz, obj);
        return obj;
    }

    private static <T> void decodeField(PbField pbField, Field field, T t) throws InstantiationException, IllegalAccessException {
        Class c = field.getType();
        field.setAccessible(true);
        PbValue v = (PbValue) pbField.getValue();
        // List<?>
        if (List.class.equals(c)) {
            decodeList(v, field, t);
            return;
        }

        // Boolean or boolean
        if (Boolean.class.equals(c) || boolean.class.equals(c)) {
            field.set(t, v.boolValue());
            return;
        }

        // Integer or int
        if (Integer.class.equals(c) || int.class.equals(c)) {
            field.set(t, v.intValue());
            return;
        }
        // Long or long
        if (Long.class.equals(c) || long.class.equals(c)) {
            field.set(t, v.longValue());
            return;
        }
        // Float or float
        if (Float.class.equals(c) || float.class.equals(c)) {
            field.set(t, v.floatValue());
            return;
        }
        // Double or double
        if (Double.class.equals(c) || double.class.equals(c)) {
            field.set(t, v.doubleValue());
            return;
        }
        // String
        if (String.class.equals(c)) {
            field.set(t, v.stringValue());
            return;
        }

        // byte[]
        if (byte[].class.equals(c)) {
            byte[] data = new byte[v.size()];
            v.write(data, 0);
            field.set(t, data);
            return;
        }
        // class user define
        Object obj = decodeObj(v, c);
        field.set(t, obj);

    }


    private static <T> void decodeField(List<PbField> pbFields, Field field, T t) throws IllegalAccessException, InstantiationException {
        if (pbFields.isEmpty()) {
            return;
        }
        int size = pbFields.size();
        Class c = field.getType();
        field.setAccessible(true);
        if (size == 1) {
            PbField f0 = pbFields.get(0);
            decodeField(f0, field, t);
            return;
        }
        // check list type
        if (!List.class.equals(c)) {
            throw new PbException(String.format("field number %d actual list, but none", pbFields.get(0).getFieldNum()));
        }

        for (PbField f : pbFields) {
            decodeField(f, field, t);
        }

    }

    public static byte[] encode(Object src) throws IllegalAccessException {
        PbMessage msg = new PbMessage(0);
        encodeObj(msg, src.getClass(), src);
        int size = msg.size();
        byte[] data = new byte[size];
        msg.write(data,0);
        return data;
    }

    public static void encode(OutputStream os, Object src) throws IOException, IllegalAccessException {
        byte[] data = encode(src);
        os.write(data);
    }

    public static void encode(ByteBuffer buffer, Object src) throws IOException, IllegalAccessException {
        byte[] data = encode(src);
        buffer.put(data);
    }

    private static void encodeObj(PbMessage msg, Class clazz, Object src) throws IllegalAccessException {
        Field[]  fields = clazz.getDeclaredFields();
        checkDuplicateFields(fields);
        for (Field f: fields){
            encodeField(msg, f, src);
        }
    }
    private static void encodeField(PbMessage msg, Field field, Object src) throws IllegalAccessException {
        Class c = field.getType();
        field.setAccessible(true);
        PbSerializedName anno = field.getAnnotation(PbSerializedName.class);
        if (anno == null) {
            return;
        }
        int fieldNum = anno.fieldNum();
        // List<?>
        if (List.class.equals(c)) {
            encodeList(msg,field, src);
            return;
        }

        // Boolean or boolean
        if (Boolean.class.equals(c) || boolean.class.equals(c)) {
            boolean v = (boolean)field.get(src);
            PbField pf = new PbField(fieldNum, PbValue.valueOf(v));
            msg.addField(pf);
            return;
        }

        // Integer or int
        if (Integer.class.equals(c) || int.class.equals(c)) {
            int v = (int)field.get(src);
            PbField pf = new PbField(fieldNum, PbValue.valueOf(v));
            msg.addField(pf);
            return;
        }
        // Long or long
        if (Long.class.equals(c) || long.class.equals(c)) {
            long v = (long)field.get(src);
            PbField pf = new PbField(fieldNum, PbValue.valueOf(v));
            msg.addField(pf);
            return;
        }
        // Float or float
        if (Float.class.equals(c) || float.class.equals(c)) {
            float v = (float)field.get(src);
            PbField pf = new PbField(fieldNum, PbValue.valueOf(v));
            msg.addField(pf);
            return;
        }
        // Double or double
        if (Double.class.equals(c) || double.class.equals(c)) {
            double v = (double)field.get(src);
            PbField pf = new PbField(fieldNum, PbValue.valueOf(v));
            msg.addField(pf);
            return;
        }
        // String
        if (String.class.equals(c)) {
            String v = (String)field.get(src);
            PbField pf = new PbField(fieldNum, PbValue.valueOf(v));
            msg.addField(pf);
            return;
        }

        // byte[]
        if (byte[].class.equals(c)) {
            byte[] data =(byte[]) field.get(src);
            PbField pf = new PbField(fieldNum, PbValue.valueOf(data,0, data.length));
            msg.addField(pf);
            return;
        }
        // class user define
        encodeObj(msg, c, field.get(src));
    }

    private static void encodeList(PbMessage msg, Field field, Object src) throws IllegalAccessException {
        PbSerializedName anno = field.getAnnotation(PbSerializedName.class);
        if (anno == null) {
            return;
        }
        int fieldNum = anno.fieldNum();
        field.setAccessible(true);
        ParameterizedType pt = (ParameterizedType) field.getGenericType();
        Type at = pt.getActualTypeArguments()[0];
        Object obj = field.get(src);
        // List<Boolean>
        if (Boolean.class.equals(at)) {
            List<Boolean> vl = (List<Boolean>) obj;
            for (Boolean v : vl){
                PbField pf = new PbField(fieldNum,PbValue.valueOf(v));
                msg.addField(pf);
            }
            return;
        }

        if (Integer.class.equals(at)) {
            List<Integer> vl = (List<Integer>) obj;
            for (Integer v : vl){
                PbField pf = new PbField(fieldNum,PbValue.valueOf(v));
                msg.addField(pf);
            }
            return;
        }

        if (Long.class.equals(at)) {
            List<Long> vl = (List<Long>) obj;
            for (Long v : vl){
                PbField pf = new PbField(fieldNum,PbValue.valueOf(v));
                msg.addField(pf);
            }
            return;
        }
        if (Float.class.equals(at)) {
            List<Float> vl = (List<Float>) obj;
            for (Float v : vl){
                PbField pf = new PbField(fieldNum,PbValue.valueOf(v));
                msg.addField(pf);
            }
            return;
        }
        if (Double.class.equals(at)) {
            List<Double> vl = (List<Double>) obj;
            for (Double v : vl){
                PbField pf = new PbField(fieldNum,PbValue.valueOf(v));
                msg.addField(pf);
            }
            return;
        }
        if (String.class.equals(at)) {
            List<String> vl = (List<String>) obj;
            for (String v : vl){
                PbField pf = new PbField(fieldNum,PbValue.valueOf(v));
                msg.addField(pf);
            }
            return;
        }
        // List<byte[]>
        if (byte[].class.equals(at)) {
            List<byte[]> vl = (List<byte[]>) obj;
            for (byte[] v : vl){
                PbField pf = new PbField(fieldNum,PbValue.valueOf(v,0,v.length));
                msg.addField(pf);
            }
            return;
        }
        // List<CustomClass>
        List list = (List) obj;
        for (Object o: list){
            encodeObj(msg, o.getClass(), o);
        }
    }

}