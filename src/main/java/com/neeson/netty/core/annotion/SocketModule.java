package com.neeson.netty.core.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 19:49
 * Description: 请求模块
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketModule {

    /**
     * 模块号
     * @return
     */
    short module();

}
