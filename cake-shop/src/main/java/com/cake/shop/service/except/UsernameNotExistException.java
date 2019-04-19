package com.cake.shop.service.except;

/**
 * 用户名不存在
 */
public class UsernameNotExistException extends ServiceException {
    public UsernameNotExistException() {
        super();
    }

    public UsernameNotExistException(String message) {
        super(message);
    }

    public UsernameNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotExistException(Throwable cause) {
        super(cause);
    }

    protected UsernameNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
