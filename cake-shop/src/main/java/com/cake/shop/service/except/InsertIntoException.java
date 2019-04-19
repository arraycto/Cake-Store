package com.cake.shop.service.except;

/**
 * insert异常
 */
public class InsertIntoException extends ServiceException{
    public InsertIntoException() {
    }

    public InsertIntoException(String message) {
        super(message);
    }

    public InsertIntoException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertIntoException(Throwable cause) {
        super(cause);
    }

    public InsertIntoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
