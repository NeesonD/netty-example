package com.neeson.netty.module.player.handler;

import com.neeson.netty.core.annotion.SocketCommand;
import com.neeson.netty.core.annotion.SocketModule;
import com.neeson.netty.core.model.Result;
import com.neeson.netty.core.session.Session;
import com.neeson.netty.module.ModuleId;
import com.neeson.netty.module.player.PlayerCmd;
import com.neeson.netty.module.player.response.PlayerResponse;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/9
 * Time: 20:13
 * Description:
 */
@SocketModule(module = ModuleId.PLAYER)
public interface PlayerHandler {

    /**
     * 创建并登录账号
     * @param session
     * @param bytes
     * @return
     */
    @SocketCommand(cmd = PlayerCmd.REGISTER_AND_LOGIN)
    Result<PlayerResponse> registerAndLogin(Session session,byte[] bytes);

    /**
     *
     * @param session
     * @param bytes
     * @return
     */
    @SocketCommand(cmd = PlayerCmd.LOGIN)
    Result<PlayerResponse> login(Session session,byte[] bytes);
}
