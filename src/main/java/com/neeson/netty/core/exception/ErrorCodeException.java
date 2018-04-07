package com.neeson.netty.core.exception;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/7
 * Time: 19:45
 * Description:
 */
public class ErrorCodeException extends  RuntimeException{

    private final int errorCode;

    public ErrorCodeException(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
