package com.neeson.netty.module.player.request;

import com.neeson.netty.core.serial.Serializer;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 21:00
 * Description: 注册消息
 */
@Data
public class RegisterRequest extends Serializer {

    private String username;
    private String password;

    @Override
    protected void read() {
        this.username = readString();
        this.password = readString();
    }

    @Override
    protected void write() {
        writeString(username);
        writeString(password);
    }
}
