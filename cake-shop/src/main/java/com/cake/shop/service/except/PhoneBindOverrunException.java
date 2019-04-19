package com.cake.shop.service.except;

/**
 * 每个电话绑定之账户数量超限
 */
public class PhoneBindOverrunException extends ServiceException{
    public PhoneBindOverrunException() {
        super();
    }

    public PhoneBindOverrunException(String message) {
        super(message);
    }

    public PhoneBindOverrunException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneBindOverrunException(Throwable cause) {
        super(cause);
    }

    protected PhoneBindOverrunException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
