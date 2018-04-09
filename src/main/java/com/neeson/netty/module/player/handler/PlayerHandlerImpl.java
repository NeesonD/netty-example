package com.neeson.netty.module.player.handler;

import com.neeson.netty.core.model.Result;
import com.neeson.netty.core.session.Session;
import com.neeson.netty.module.player.response.PlayerResponse;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/9
 * Time: 20:18
 * Description:
 */
public class PlayerHandlerImpl implements PlayerHandler {
    @Override
    public Result<PlayerResponse> registerAndLogin(Session session, byte[] bytes) {
        return null;
    }

    @Override
    public Result<PlayerResponse> login(Session session, byte[] bytes) {
        return null;
    }
}
