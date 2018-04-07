package com.neeson.netty.module.chat.request;

import com.neeson.netty.core.serial.Serializer;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 20:50
 * Description: 聊天室消息
 */
@Data
public class PublicChatRequest extends Serializer {

    private String content;


    @Override
    protected void read() {
        this.content = readString();
    }

    @Override
    protected void write() {
        writeString(content);
    }
}
