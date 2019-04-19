package com.cake.shop.service.except;

/**
 * 蛋糕找不到
 */
public class CakeNullException extends ServiceException {
    public CakeNullException() {
    }

    public CakeNullException(String message) {
        super(message);
    }

    public CakeNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public CakeNullException(Throwable cause) {
        super(cause);
    }

    public CakeNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
