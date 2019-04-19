package com.cake.shop.service.except;

/**
 * 异常:关键字为空
 */
public class KeyWordNullException extends ServiceException{
    public KeyWordNullException() {
        super();
    }

    public KeyWordNullException(String message) {
        super(message);
    }

    public KeyWordNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyWordNullException(Throwable cause) {
        super(cause);
    }

    protected KeyWordNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
