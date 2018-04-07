package com.neeson.netty.core.codc;

import com.neeson.netty.core.model.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 20:27
 * Description: 响应值解码器
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * |  包头	|  模块号      |  命令号    |  结果码    |  长度       |   数据     |
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * </pre>
 */
public class ResponseDecoder extends ByteToMessageDecoder {

    private static final int BASE_LENGTH = 4 + 2+ 2+4+4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (true){
            if (byteBuf.readableBytes() < BASE_LENGTH){
                break;
            }else {
                int beginIndex;
                while (true){
                    beginIndex = byteBuf.readerIndex();
                    byteBuf.markReaderIndex();
                    //判断数据是否从头部开始
                    if (byteBuf.readInt() == ConstantValue.HEADER_FLAG){
                        break;
                    }
                    byteBuf.resetReaderIndex();
                    byteBuf.readByte();

                    if (byteBuf.readableBytes() < BASE_LENGTH){
                        return;
                    }
                }
                short module = byteBuf.readShort();
                short cmd = byteBuf.readShort();

                int stateCode = byteBuf.readInt();

                int length = byteBuf.readInt();
                if (length < 0){
                    ctx.close();
                }

                //数据包未到齐
               if (byteBuf.readableBytes() < length){
                    byteBuf.readerIndex(beginIndex);
                    return;
               }

                byte[] bytes = new byte[length];
               byteBuf.readBytes(bytes);

                Response response = new Response();
                response.setModule(module);
                response.setCmd(cmd);
                response.setStateCode(stateCode);
                response.setData(bytes);
                list.add(response);
            }

        }
    }
}
