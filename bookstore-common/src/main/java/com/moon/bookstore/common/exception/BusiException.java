package com.moon.bookstore.common.exception;

/**
 * 业务异常
 *
 * @author yujiangtao
 * @date 2021/7/23 下午3:37
 */
@SuppressWarnings("unused")
public class BusiException extends RuntimeException {

    public BusiException() {}

    public BusiException(String message) {
        super(message);
    }

    public BusiException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusiException(Throwable cause) {
        super(cause);
    }
}
