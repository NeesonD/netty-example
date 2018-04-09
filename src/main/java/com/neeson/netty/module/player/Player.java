package com.neeson.netty.module.player;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/9
 * Time: 20:42
 * Description:
 */
@Data
public class Player {

    private long playerId;

    /**
     * 玩家名
     */
    private String playerName;

    /**
     * 密码
     */
    private String password;

    /**
     * 等级
     */
    private int level;

    /**
     * 经验
     */
    private int exp;

}
