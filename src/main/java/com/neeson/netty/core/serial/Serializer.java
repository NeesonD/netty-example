package com.neeson.netty.core.serial;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
import sun.java2d.pipe.ValidatePipe;

import javax.xml.crypto.Data;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/6
 * Time: 17:51
 * Description: 自定义序列化
 */
public abstract class Serializer {

    public static final Charset CHARSET = Charset.forName("UTF-8");

    protected ByteBuf writeBuffer;

    protected ByteBuf readBuffer;

    /**
     * 反序列化具体实现
     */
    protected abstract void read();

    /**
     * 序列化具体实现
     */
    protected abstract void write();

    /**
     * 从byte数组获取数据
     * @param bytes
     * @return
     */
    public Serializer readFromBytes(byte[] bytes){
        readBuffer = BufferFactory.getByteBuf(bytes);
        read();
        readBuffer.clear();
        ReferenceCountUtil.release(readBuffer);
        return this;
    }

    /**
     * 从buff中获取数据
     * @param byteBuf
     */
    public void readFromBuff(ByteBuf byteBuf){
        this.readBuffer = byteBuf;
        read();
    }


    /**
     * 写入本地buff
     * @return
     */
    public ByteBuf writeToLocalBuff(){
        writeBuffer = BufferFactory.getByteBuf();
        write();
        return writeBuffer;
    }

    /**
     * 写入目标buff
     * @param byteBuf
     * @return
     */
    public ByteBuf writeToTargerBuff(ByteBuf byteBuf){
        writeBuffer = byteBuf;
        write();
        return writeBuffer;
    }

    /**
     * 返回buff数组
     * @return
     */
    public byte[] getBytes(){
        writeToLocalBuff();
        byte[] bytes;
        if (writeBuffer.writerIndex() == 0){
            bytes = new byte[0];
        }else {
            bytes = new byte[writeBuffer.writerIndex()];
            //TODO readBytes
            writeBuffer.readBytes(bytes);
        }
        writeBuffer.clear();
        ReferenceCountUtil.release(writeBuffer);
        return bytes;
    }


    public byte readByte() {
        return readBuffer.readByte();
    }

    public short readShort() {
        return readBuffer.readShort();
    }

    public int readInt() {
        return readBuffer.readInt();
    }

    public long readLong() {
        return readBuffer.readLong();
    }

    public float readFloat() {
        return readBuffer.readFloat();
    }

    public double readDouble() {
        return readBuffer.readDouble();
    }

    public String readString(){
        int size = readBuffer.readShort();
        if (size <= 0){
            return "";
        }
        byte[] bytes =  new byte[size];
        readBuffer.readBytes(bytes);
        return bytes.toString();
    }

    public <T> List<T> readList(Class<T> clz){
        List<T> list = new ArrayList<>();
        int size = readBuffer.readShort();
        for (int i = 0; i <size ; i++) {
            list.add(read(clz));
        }
        return list;
    }

    public <K,V> Map<K,V> readMap(Class<K> keyClz,Class<V> valueClz){
        Map<K,V> map = new HashMap<>();
        int size = readBuffer.readShort();
        for (int i = 0; i <size ; i++) {
            K key = read(keyClz);
            V value = read(valueClz);
            map.put(key,value);
        }
        return map;
    }


    public <I> I read(Class<I> clz){
        Object t = null;
        if (clz == int.class || clz == Integer.class){
            t = readBuffer.readInt();
        }else if (clz == byte.class || clz == Byte.class){
            t = this.readByte();
        } else if (clz == short.class || clz == Short.class){
            t = this.readShort();
        } else if (clz == long.class || clz == Long.class){
            t = this.readLong();
        } else if (clz == float.class || clz == Float.class){
            t = readFloat();
        } else if (clz == double.class || clz == Double.class){
            t = readDouble();
        } else if (clz == String.class ){
            t = readString();
        }else if(Serializer.class.isAssignableFrom(clz)){
            try {
                byte hasObject = this.readBuffer.readByte();
                if (hasObject == 1){
                    Serializer temp = (Serializer) clz.newInstance();
                    temp.readFromBuff(this.readBuffer);
                    t = temp;
                }else {
                    t = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            throw new RuntimeException(String.format("不支持类型:[%s]", clz));
        }
        return (I) t;
    }


    public Serializer writeByte(byte b){
        writeBuffer.writeByte(b);
        return this;
    }

    public Serializer writeInt(Integer value) {
        writeBuffer.writeInt(value);
        return this;
    }

    public Serializer writeShort(Short value){
        writeBuffer.writeShort(value);
        return this;
    }

    public Serializer writeLong(Long value) {
        writeBuffer.writeLong(value);
        return this;
    }

    public Serializer writeFloat(Float value) {
        writeBuffer.writeFloat(value);
        return this;
    }

    public Serializer writeDouble(Double value) {
        writeBuffer.writeDouble(value);
        return this;
    }

    public Serializer writeString(String value){
        if (value == null || value.isEmpty()){
            writeShort((short) 0);
            return this;
        }
        byte[] bytes = value.getBytes();
        short len = (short) bytes.length;
        writeBuffer.writeShort(len);
        writeBuffer.writeBytes(bytes);
        return this;
    }

    public <T> Serializer writeList(List<T> list){
        if (isEmpty(list)){
            writeBuffer.writeShort(0);
            return this;
        }
        writeBuffer.writeShort(list.size());
        for (T item : list) {
            writeObject(item);
        }
        return this;
    }

    public <K,V> Serializer writeMap(Map<K,V> map){
        if (isEmpty(map)){
            writeBuffer.writeShort(0);
            return this;
        }
        writeBuffer.writeShort(map.size());
        for (Map.Entry<K,V> entry: map.entrySet()){
            writeObject(entry.getKey());
            writeObject(entry.getValue());
        }
        return this;
    }

    public Serializer writeObject(Object object){
        if (object == null){
            writeByte((byte) 0);
        }else {
            if (object instanceof Integer) {
                writeInt((int) object);
                return this;
            }

            if (object instanceof Long) {
                writeLong((long) object);
                return this;
            }

            if (object instanceof Short) {
                writeShort((short) object);
                return this;
            }

            if (object instanceof Byte) {
                writeByte((byte) object);
                return this;
            }

            if (object instanceof String){
                String value = (String) object;
                writeString(value);
            }
            if (object instanceof Serializer){
                writeByte((byte) 1);
                Serializer value = (Serializer) object;
                value.writeToTargerBuff(writeBuffer);
                return this;
            }
            throw new RuntimeException("不可序列化的类型"+object.getClass());
        }
        return this;
    }


    private <T> boolean isEmpty(Collection<T> c){
        return c == null || c.size() == 0;
    }

    public <K,V> boolean isEmpty(Map<K,V> map){
        return map == null || map.size() == 0;
    }
}
