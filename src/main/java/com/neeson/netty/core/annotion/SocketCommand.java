package com.neeson.netty.core.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 19:47
 * Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketCommand {

    /**
     * 命令号
     * @return
     */
    short cmd();

}
