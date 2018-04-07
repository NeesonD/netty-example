package com.neeson.netty.core.codc;/**
 * Create by neeson on 2018/4/6
 */

import com.neeson.netty.core.model.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/6
 * Time: 11:55
 * Description: 数据包解码器
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * |  包头	|  模块号      |  命令号    |   长度     |   数据       |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * </pre>
 * 包头4字节
 * 模块号2字节
 * 命令号2字节
 * 长度4字节(数据部分占有字节数量)
 */
public class RequestDecoder extends ByteToMessageDecoder {

    /**
     * 数据包基本长度
     */
    public static int BASE_LENGTH = 4 + 2 + 2 + 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (true){
            if (byteBuf.readableBytes() >= BASE_LENGTH){
                int beginIndex;
                while (true){
                    beginIndex = byteBuf.readerIndex();
                    byteBuf.markReaderIndex();
                    if (byteBuf.readInt() == ConstantValue.HEADER_FLAG){
                        break;
                    }
                    byteBuf.resetReaderIndex();
                    byteBuf.readByte();
                    if (byteBuf.readableBytes() < BASE_LENGTH){
                        return;
                    }
                }
                //读取模块与指令
                short module = byteBuf.readShort();
                short cmd = byteBuf.readShort();

                //获取数据长度
                int length = byteBuf.readInt();
                if (length < 0){
                    ctx.close();
                }

                //数据没到齐
                if (byteBuf.readableBytes() < length){
                    byteBuf.readerIndex(beginIndex);
                    return;
                }

                //读数据
                byte[] bytes = new byte[length];
                byteBuf.readBytes(bytes);

                Request message = new Request();
                message.setModule(module);
                message.setCmd(cmd);
                message.setData(bytes);
                //解析出消息对象，向下个handler传递
                list.add(message);
            }else {
                break;
            }
        }
    }
}
