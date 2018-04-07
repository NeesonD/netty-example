package com.neeson.netty.core.model;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/6
 * Time: 16:49
 * Description: 状态码
 */
public interface ResultCode {
	
	/**
	 * 成功
	 */
	byte SUCCESS = 0;
	
	/**
	 * 找不到命令
	 */
	byte NO_INVOKER = 1;
	
	/**
	 * 参数异常
	 */
	byte AGRUMENT_ERROR = 2;
	
	/**
	 * 未知异常
	 */
	byte UNKOWN_EXCEPTION = 3;
	
	/**
	 * 玩家名或密码不能为空
	 */
	byte PLAYERNAME_NULL = 4;
	
	/**
	 * 玩家名已使用
	 */
	byte PLAYER_EXIST = 5;
	
	/**
	 * 玩家不存在
	 */
	byte PLAYER_NO_EXIST = 6;
	
	/**
	 * 密码错误
	 */
	byte PASSWARD_ERROR = 7;
	
	/**
	 * 您已登录
	 */
	byte HAS_LOGIN = 8;
	
	/**
	 * 登录失败
	 */
	byte LOGIN_FAIL = 9;
	
	/**
	 * 玩家不在线
	 */
	byte PLAYER_NO_ONLINE = 10;
	
	/**
	 * 请先登录
	 */
	byte LOGIN_PLEASE = 11;
	
	/**
	 * 不能私聊自己
	 */
	byte CAN_CHAT_YOUSELF = 12;
}
