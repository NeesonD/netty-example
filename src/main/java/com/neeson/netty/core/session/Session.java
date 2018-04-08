package com.neeson.netty.core.session;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/8
 * Time: 20:47
 * Description:
 */
public interface Session {

    /**
     * 会话绑定对象
     * @return
     */
    Object getAttachment();

    /**
     * 绑定对象
     * @param attachment
     */
    void setAttachment(Object attachment);

    /**
     * 移除绑定对象
     * @param attachment
     */
    void removeAttachment(Object attachment);

    /**
     * 向会话中写入消息
     * @param message
     */
    void write(Object message);

    /**
     * 判断会话是否在链接中
     * @return
     */
    boolean isConnected();

    /**
     * 关闭
     */
    void close();
}
