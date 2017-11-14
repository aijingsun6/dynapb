package org.alking.dynapb;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * <p>support field type: </p>
 * <ul>
 *     <li>{@link Boolean} or boolean </li>
 *     <li>{@link Integer} or int </li>
 *     <li>{@link Long} or long </li>
 *     <li>{@link Float} or float </li>
 *     <li>{@link Double} or double </li>
 *     <li>{@link String}</li>
 *     <li>byte[]</li>
 *     <li> CustomClass </li>
 *     <li>{@link List}&lt;{@link Boolean}&gt; </li>
 *     <li>{@link List}&lt;{@link Integer}&gt; </li>
 *     <li>{@link List}&lt;{@link Long}&gt; </li>
 *     <li>{@link List}&lt;{@link Float}&gt; </li>
 *     <li>{@link List}&lt;{@link Double}&gt; </li>
 *     <li>{@link List}&lt;{@link String}&gt; </li>
 *     <li>{@link List}&lt;byte[]&gt; </li>
 *     <li>{@link List}&lt;CustomClass&gt; </li>
 * </ul>
 */
public class DynaPb {


    public static <T> T decode(byte[] data, int offset, int size,Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();
        PbMessage msg = new PbMessage();
        msg.read(data, offset, size);
        decode(msg, clazz, t);
        return t;
    }

    static <T> void decode(PbMessage msg, Class<T> clazz, T t) throws IllegalAccessException, InstantiationException {
        Field[] fields = clazz.getDeclaredFields();
        Map<Integer, String> map = new HashMap<>();
        for (Field f: fields){
            String name = f.getName();
            PbSerializedName anno = f.getAnnotation(PbSerializedName.class);
            if(anno == null){
                continue;
            }
            int fieldNum = anno.fieldNum();
            // check duplicate field number
            if(map.containsKey(fieldNum)){
                throw new PbException(String.format("field number %d has duplicate field %s and %s",fieldNum, map.get(fieldNum), name));
            }
            map.put(fieldNum,name);
            decodeField(msg.getFieldList(fieldNum), f, t);
        }
    }

    /**
     * <ul>
     *     <li>{@link List}&lt;{@link Boolean}&gt; </li>
     *     <li>{@link List}&lt;{@link Integer}&gt; </li>
     *     <li>{@link List}&lt;{@link Long}&gt; </li>
     *     <li>{@link List}&lt;{@link Float}&gt; </li>
     *     <li>{@link List}&lt;{@link Double}&gt; </li>
     *     <li>{@link List}&lt;{@link String}&gt; </li>
     *     <li>{@link List}&lt;byte[]&gt; </li>
     *     <li>{@link List}&lt;CustomClass&gt; </li>
     * </ul>
     * @param value
     * @param field
     * @param t
     * @param <T>
     * @throws IllegalAccessException
     */
    static <T> void decodeList(PbValue value, Field field, T t) throws IllegalAccessException, InstantiationException {
        field.setAccessible(true);
        ParameterizedType pt = (ParameterizedType)field.getGenericType();
        Type at = pt.getActualTypeArguments()[0];

        Object obj = field.get(t);

        // List<Boolean>
        if(Boolean.class.equals(at)){
            List<Boolean> bl = (List<Boolean>)obj;
            bl.add(value.boolValue());
            return;
        }

        if(Integer.class.equals(at)){
            List<Integer> il = (List<Integer>)field.get(t);
            il.add(value.intValue());
            return;
        }

        if(Long.class.equals(at)){
            List<Long> ll = (List<Long>)obj;
            ll.add(value.longValue());
            return;
        }
        if(Float.class.equals(at)){
            List<Float> fl = (List<Float>)obj;
            fl.add(value.floatValue());
            return;
        }
        if(Double.class.equals(at)){
            List<Double> dl = (List<Double>)obj;
            dl.add(value.doubleValue());
            return;
        }
        if(String.class.equals(at)){
            List<String> sl = (List<String>)obj;
            sl.add(value.stringValue());
            return;
        }
        // List<byte[]>
        if(byte[].class.equals(at)){
            List<byte[]> bsl = (List<byte[]>)obj;
            byte[] data = new byte[value.size()];
            value.write(data,0);
            bsl.add(data);
            return;
        }
        // List<CustomClass>
        Class<?> c = (Class<?>)at;
        List list = (List)obj;
        Object co = decodeObj(value, c);
        list.add(co);
    }

    static <T> T decodeObj(PbValue value, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        WireType wt = value.wireType();
        if(!wt.equals(WireType.BYTES)){
            throw new PbException( String.format("illegal wire type %s",wt));
        }
        PbBytes bytes  = (PbBytes)value;
        PbMessage msg = new PbMessage();
        msg.read(bytes.getBytes(), bytes.getOffset(), bytes.getSize());
        T obj = clazz.newInstance();
        decode(msg,clazz, obj);
        return obj;
    }

    static <T> void decodeField(PbField pbField, Field field, T t) throws InstantiationException, IllegalAccessException {
        Class c = field.getType();
        field.setAccessible(true);
        PbValue v = (PbValue) pbField.getValue();
        // List<?>
        if(List.class.equals(c)){
            decodeList(v, field, t);
            return;
        }

        // Boolean or boolean
        if(Boolean.class.equals(c) ||  boolean.class.equals(c)){
            field.set(t, v.boolValue());
            return;
        }

        // Integer or int
        if(Integer.class.equals(c) || int.class.equals(c)){
            field.set(t, v.intValue());
            return;
        }
        // Long or long
        if(Long.class.equals(c) || long.class.equals(c)){
            field.set(t, v.longValue());
            return;
        }
        // Float or float
        if(Float.class.equals(c) || float.class.equals(c)){
            field.set(t, v.floatValue());
            return;
        }
        // Double or double
        if(Double.class.equals(c) || double.class.equals(c)){
            field.set(t, v.doubleValue());
            return;
        }
        // String
        if(String.class.equals(c)){
            field.set(t, v.stringValue());
            return;
        }

        // byte[]
        if(byte[].class.equals(c)){
            byte[] data = new byte[v.size()];
            v.write(data, 0);
            field.set(t, data);
            return;
        }
        // class user define
        Object obj = decodeObj(v, c);
        field.set(t, obj);

    }


    static <T> void decodeField(List<PbField> pbFields, Field field, T t) throws IllegalAccessException, InstantiationException {
        if(pbFields.isEmpty()){
            return;
        }
        int size = pbFields.size();
        Class c = field.getType();
        field.setAccessible(true);
        if(size == 1){
            PbField f0 = pbFields.get(0);
            decodeField(f0, field, t);
            return;
        }
        // check list type
        if(!List.class.equals(c)){
            throw new PbException(String.format("field number %d actual list, but none",pbFields.get(0).getFieldNum()));
        }

        for (PbField f: pbFields){
            decodeField(f, field, t);
        }

    }


}