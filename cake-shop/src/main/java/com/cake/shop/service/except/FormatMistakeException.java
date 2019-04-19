package com.cake.shop.service.except;

/**
 * 数据形式错误异常.如价格一栏输入字符
 */
public class FormatMistakeException extends ServiceException {
    public FormatMistakeException() {
    }

    public FormatMistakeException(String message) {
        super(message);
    }

    public FormatMistakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatMistakeException(Throwable cause) {
        super(cause);
    }

    public FormatMistakeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
