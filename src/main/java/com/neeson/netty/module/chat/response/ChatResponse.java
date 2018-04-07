package com.neeson.netty.module.chat.response;

import com.neeson.netty.core.serial.Serializer;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 20:51
 * Description: 聊天消息
 */
@Data
public class ChatResponse extends Serializer {

    private long fromUserId;

    private String fromUserName;

    private String toUserName;

    private byte chatType;

    private String message;

    @Override
    protected void read() {
        this.fromUserId = readLong();
        this.fromUserName = readString();
        this.toUserName = readString();
        this.chatType = readByte();
        this.message = readString();
    }

    @Override
    protected void write() {
        writeLong(fromUserId);
        writeString(fromUserName);
        writeString(toUserName);
        writeByte(chatType);
        writeString(message);
    }


}
