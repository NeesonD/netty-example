package com.neeson.netty.core.codc;

import com.neeson.netty.core.model.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/6
 * Time: 11:57
 * Description: 请求数据编码器
 */
public class RequestEncoder extends MessageToByteEncoder<Request>{
    @Override
    protected void encode(ChannelHandlerContext ctx, Request request, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(ConstantValue.HEADER_FLAG);
        byteBuf.writeShort(request.getModule());
        byteBuf.writeShort(request.getCmd());
        int length = request.getData() == null ? 0:request.getData().length;
        if (length == 0){
            byteBuf.writeInt(length);
        }else {
            byteBuf.writeInt(length);
            byteBuf.writeBytes(request.getData());
        }

    }
}
