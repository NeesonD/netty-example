package com.neeson.netty.core.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/6
 * Time: 16:49
 * Description: 回复消息
 */
@Data
public class Response {

    /**
     * 模块号
     */
    private short module;

    /**
     * 命令
     */
    private short cmd;

    /**
     * 数据
     */
    private byte[] data;

    /**
     * 状态值
     */
    private int stateCode = ResultCode.SUCCESS;

    public Response() {
    }

    public Response(Request message) {
        this.module = message.getModule();
        this.cmd = message.getCmd();
    }

    public Response(short module, short cmd, byte[] data){
        this.module = module;
        this.cmd = cmd;
        this.data = data;
    }

}
