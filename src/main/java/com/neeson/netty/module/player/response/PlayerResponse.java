package com.neeson.netty.module.player.response;

import com.neeson.netty.core.serial.Serializer;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 21:03
 * Description:
 */
@Data
public class PlayerResponse extends Serializer {

    private long userId;

    private String userName;

    private int level;

    private int exp;

    @Override
    protected void read() {
        this.userId = readLong();
        this.userName = readString();
        this.level = readInt();
        this.exp = readInt();
    }

    @Override
    protected void write() {
        writeLong(userId);
        writeString(userName);
        writeInt(level);
        writeInt(exp);
    }
}
