package com.cake.shop.service.except;

/**
 * 库存无备货异常
 */
public class CakeAmountZeroException extends ServiceException {
    public CakeAmountZeroException() {
    }

    public CakeAmountZeroException(String message) {
        super(message);
    }

    public CakeAmountZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public CakeAmountZeroException(Throwable cause) {
        super(cause);
    }

    public CakeAmountZeroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
