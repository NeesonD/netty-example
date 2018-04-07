package com.neeson.netty.module.chat;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 20:57
 * Description: 聊天模块
 */
public interface ChatCmd {

    /**
     * 广播消息
     */
    short PUBLIC_CHAT = 1;

    /**
     * 单聊消息
     */
    short SINGLE_CAHT = 2;

    /**
     * 消息推送
     */
    short PUSHCHAT = 101;
}
