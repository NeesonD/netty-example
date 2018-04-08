package com.neeson.netty;

import com.neeson.netty.core.model.Request;
import com.neeson.netty.core.model.Response;
import com.neeson.netty.core.session.Session;
import com.neeson.netty.module.ModuleId;
import com.neeson.netty.scanner.Invoker;
import com.neeson.netty.scanner.InvokerHodler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/8
 * Time: 21:40
 * Description:
 */
@Slf4j
public class ServerHandler  extends SimpleChannelInboundHandler<Request>{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Request request) throws Exception {

    }


    private void handlerMessage(Session session,Request request){
        Response response = new Response(request);
        log.info("====>"+request.getModule()+"=======>"+request.getCmd());

        Invoker invoker = InvokerHodler.getInvoker(request.getModule(), request.getCmd());
        if (invoker!=null){
            Object result;
            if (request.getModule() == ModuleId.PLAYER){
                result = invoker.invoke(session,request.getData());
            }else {
                Object attachment = session.getAttachment();
                if (attachment != null){
                }
            }
        }


    }
}
