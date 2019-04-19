package com.cake.shop.service.except;

/**
 * 账号已被注销
 */
public class AccountHasBeenCancelException extends ServiceException {
    public AccountHasBeenCancelException() {
        super();
    }

    public AccountHasBeenCancelException(String message) {
        super(message);
    }

    public AccountHasBeenCancelException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountHasBeenCancelException(Throwable cause) {
        super(cause);
    }

    protected AccountHasBeenCancelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
