package com.neeson.netty.module.chat.response;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 20:56
 * Description: 消息类型
 */
public interface ChatType {

    /**
     * 聊天室
     */
    byte PUBLIC_CHAT = 0;

    /**
     * 单聊
     */
    byte SING_CAHT = 1;
}
