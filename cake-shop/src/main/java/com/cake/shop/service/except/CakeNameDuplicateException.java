package com.cake.shop.service.except;

/**
 * 蛋糕名重复异常
 */
public class CakeNameDuplicateException extends ServiceException {
    public CakeNameDuplicateException() {
    }

    public CakeNameDuplicateException(String message) {
        super(message);
    }

    public CakeNameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CakeNameDuplicateException(Throwable cause) {
        super(cause);
    }

    public CakeNameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
