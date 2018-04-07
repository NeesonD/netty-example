package com.neeson.netty.core.serial;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;

import java.nio.ByteOrder;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/6
 * Time: 17:55
 * Description: buff工厂
 */
public class BufferFactory {

    //字节序(分大端和小端)
    public static ByteOrder BYTE_ORDER = ByteOrder.BIG_ENDIAN;

    private static ByteBufAllocator bufAllocator = PooledByteBufAllocator.DEFAULT;

    /**
     * 获取一个buff
     * @return
     */
    public static ByteBuf getByteBuf(){
        ByteBuf byteBuf = bufAllocator.heapBuffer();
        byteBuf.order(BYTE_ORDER);
        return byteBuf;
    }

    /**
     * 将数据写到buffer
     * @param bytes
     * @return
     */
    public static ByteBuf getByteBuf(byte[] bytes){
        ByteBuf byteBuf = bufAllocator.heapBuffer();
        byteBuf.order(BYTE_ORDER);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

}
