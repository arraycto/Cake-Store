package com.cake.shop.service.except;

/**
 * 重复操作异常
 */
public class RepetitiveOperationException extends ServiceException{
    public RepetitiveOperationException() {
        super();
    }

    public RepetitiveOperationException(String message) {
        super(message);
    }

    public RepetitiveOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepetitiveOperationException(Throwable cause) {
        super(cause);
    }

    protected RepetitiveOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
