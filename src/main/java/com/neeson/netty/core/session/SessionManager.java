package com.neeson.netty.core.session;

import com.neeson.netty.core.model.Response;
import com.neeson.netty.core.serial.Serializer;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/8
 * Time: 20:59
 * Description:
 */
public class SessionManager {

    private static final Map<Long,Session> onlineSessions = new ConcurrentHashMap<>();

    /**
     * 加入
     * @param playerId
     * @param session
     * @return
     */
    public static boolean putSession(long playerId,Session session){
        if (!onlineSessions.containsKey(playerId)){
            boolean success = onlineSessions.putIfAbsent(playerId,session) == null? true: false;
            return success;
        }
        return false;
    }

    /**
     * 移除
     * @param playerId
     * @return
     */
    public static Session removeSession(Long playerId){
        return onlineSessions.remove(playerId);
    }

    /**
     * 发送消息
     * @param toUser
     * @param module
     * @param cmd
     * @param message
     * @param <T>
     */
    public static <T extends Serializer> void sendMessage(long toUser,short module,short cmd,T message){
        Session session = onlineSessions.get(toUser);
        if (session != null && session.isConnected()){
            Response response = new Response();
            response.setModule(module);
            response.setCmd(cmd);
            response.setData(message.getBytes());
            session.write(response);
        }
    }

    /**
     * 是否在线
     * @param playerId
     * @return
     */
    public static boolean isOnlinePlayer(long playerId){
        return onlineSessions.containsKey(playerId);
    }


    public static Set<Long> getOnlinePlayers(){
        return Collections.unmodifiableSet( onlineSessions.keySet());
    }



}
