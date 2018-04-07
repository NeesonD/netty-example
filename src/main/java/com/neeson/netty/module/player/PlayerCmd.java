package com.neeson.netty.module.player;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 21:04
 * Description:
 */
public interface PlayerCmd {

    /**
     * 创建并登录帐号
     */
    short REGISTER_AND_LOGIN = 1;

    /**
     * 登录帐号
     */
    short LOGIN = 2;

}
