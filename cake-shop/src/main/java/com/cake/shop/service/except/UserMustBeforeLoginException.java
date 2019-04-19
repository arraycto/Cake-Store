package com.cake.shop.service.except;

/**
 * 用户必须先进行登录方可执行操作,
 * 用户含普通会员和领事
 */
public class UserMustBeforeLoginException extends ServiceException {
    public UserMustBeforeLoginException() {
    }

    public UserMustBeforeLoginException(String message) {
        super(message);
    }

    public UserMustBeforeLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserMustBeforeLoginException(Throwable cause) {
        super(cause);
    }

    public UserMustBeforeLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
