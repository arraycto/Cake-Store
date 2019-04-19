package com.cake.shop.service.except;

/**
 * 权限异常
 */
public class JurisdictionException extends ServiceException {
    public JurisdictionException() {
        super();
    }

    public JurisdictionException(String message) {
        super(message);
    }

    public JurisdictionException(String message, Throwable cause) {
        super(message, cause);
    }

    public JurisdictionException(Throwable cause) {
        super(cause);
    }

    protected JurisdictionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
