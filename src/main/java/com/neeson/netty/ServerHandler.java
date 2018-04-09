package com.neeson.netty;

import com.neeson.netty.core.model.Request;
import com.neeson.netty.core.model.Response;
import com.neeson.netty.core.model.Result;
import com.neeson.netty.core.model.ResultCode;
import com.neeson.netty.core.serial.Serializer;
import com.neeson.netty.core.session.Session;
import com.neeson.netty.core.session.SessionImpl;
import com.neeson.netty.core.session.SessionManager;
import com.neeson.netty.module.ModuleId;
import com.neeson.netty.module.player.Player;
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
        handlerMessage(new SessionImpl(channelHandlerContext.channel()),request);
    }


    private void handlerMessage(Session session,Request request){
        Response response = new Response(request);
        log.info("====>"+request.getModule()+"=======>"+request.getCmd());

        Invoker invoker = InvokerHodler.getInvoker(request.getModule(), request.getCmd());
        if (invoker!=null){
            Result<?> result ;
            if (request.getModule() == ModuleId.PLAYER){
                result = (Result<?>) invoker.invoke(session,request.getData());
            }else {
                Object attachment = session.getAttachment();
                if (attachment != null){
                    Player player= (Player) attachment;
                    result = (Result<?>) invoker.invoke(player.getPlayerId(),request.getData());
                }else {
                    response.setStateCode(ResultCode.LOGIN_PLEASE);
                    session.write(response);
                    return;
                }
            }
            if (result.getResultCode() == ResultCode.SUCCESS){
                Object content = result.getContent();
                if (content != null){
                    Serializer content2 = (Serializer) content;
                    response.setData(((Serializer) content).getBytes());
                }else {
                    System.out.println("不可识别的传输对象");
                }
            }else {
                response.setStateCode(result.getResultCode());
                session.write(response);
                return;
            }
        }else {
            response.setStateCode(ResultCode.NO_INVOKER);
            session.write(response);
            return;
        }


    }

    /**
     * 移除会话
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionImpl session = new SessionImpl(ctx.channel());
        Object attachment = session.getAttachment();
        if (attachment != null){
            Player player = (Player) attachment;
            SessionManager.removeSession(player.getPlayerId());
        }
    }
}
