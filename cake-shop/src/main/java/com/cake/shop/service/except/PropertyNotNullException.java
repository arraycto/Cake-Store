package com.cake.shop.service.except;

/**
 * 属性不可为空异常
 */
public class PropertyNotNullException extends ServiceException{
    public PropertyNotNullException() {
    }

    public PropertyNotNullException(String message) {
        super(message);
    }

    public PropertyNotNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyNotNullException(Throwable cause) {
        super(cause);
    }

    public PropertyNotNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
