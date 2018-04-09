package com.neeson.netty.module.chat.handler;

import com.neeson.netty.core.annotion.SocketCommand;
import com.neeson.netty.core.annotion.SocketModule;
import com.neeson.netty.core.model.Result;
import com.neeson.netty.core.session.Session;
import com.neeson.netty.module.ModuleId;
import com.neeson.netty.module.chat.ChatCmd;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/9
 * Time: 20:20
 * Description:
 */
@SocketModule(module = ModuleId.CHAT)
public interface ChatHandler {

    /**
     * 广播消息
     *
     * @param playerId
     * @param bytes
     * @return
     */
    @SocketCommand(cmd = ChatCmd.PUBLIC_CHAT)
    Result<?> publicChat(long playerId, byte[] bytes);

    /**
     * 私人消息
     * @param playerId
     * @param bytes
     * @return
     */
    @SocketCommand(cmd = ChatCmd.SINGLE_CAHT)
    Result<?> singleChat(long playerId, byte[] bytes);
}
