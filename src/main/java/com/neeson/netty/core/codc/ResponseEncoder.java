package com.neeson.netty.core.codc;

import com.neeson.netty.core.model.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 20:41
 * Description: 编码器
 */
public class ResponseEncoder extends MessageToByteEncoder<Response> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Response response, ByteBuf byteBuf) throws Exception {

        byteBuf.writeInt(ConstantValue.HEADER_FLAG);
        byteBuf.writeShort(response.getModule());
        byteBuf.writeShort(response.getCmd());
        byteBuf.writeInt(response.getStateCode());

        int length = response.getData() == null ? 0: response.getData().length;
        if (length > 0){
            byteBuf.writeBytes(response.getData());
        }


    }
}
