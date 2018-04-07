package com.neeson.netty.core.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/6
 * Time: 11:58
 * Description: 消息对象
 */
@Data
public class Request {

    /**
     * 模块号
     */
    private short module;

    /**
     * 命令号
     */
    private short cmd;


    /**
     * 数据
     */
    private byte[] data;



}
