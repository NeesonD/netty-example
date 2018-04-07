package com.neeson.netty.module.chat.request;

import com.neeson.netty.core.serial.Serializer;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 20:46
 * Description: 单聊消息
 */
@Data
public class SingleChatRequest extends Serializer {

    /**
     * 目标
     */
    private long toUser;

    /**
     * 内容
     */
    private String content;

    @Override
    protected void read() {
        this.toUser = readLong();
        this.content = readString();
    }

    @Override
    protected void write() {
        writeLong(toUser);
        writeString(content);
    }



}
