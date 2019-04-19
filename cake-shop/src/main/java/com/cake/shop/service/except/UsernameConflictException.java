package com.cake.shop.service.except;

/**
 * 用户名冲突,必须唯一
 */
public class UsernameConflictException extends ServiceException{
    public UsernameConflictException() {
        super();
    }

    public UsernameConflictException(String message) {
        super(message);
    }

    public UsernameConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameConflictException(Throwable cause) {
        super(cause);
    }

    protected UsernameConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
