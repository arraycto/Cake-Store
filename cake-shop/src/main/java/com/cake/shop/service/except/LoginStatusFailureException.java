package com.cake.shop.service.except;

/**
 * 登录状态失效
 */
public class LoginStatusFailureException extends ServiceException{
    public LoginStatusFailureException() {
    }

    public LoginStatusFailureException(String message) {
        super(message);
    }

    public LoginStatusFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginStatusFailureException(Throwable cause) {
        super(cause);
    }

    public LoginStatusFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
