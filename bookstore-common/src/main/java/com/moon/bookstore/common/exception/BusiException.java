package com.moon.bookstore.common.exception;

/**
 * 业务异常
 *
 * @author yujiangtao
 * @date 2021/7/23 下午3:37
 */
public class BusiException extends RuntimeException {

    @SuppressWarnings("unused")
    public BusiException() {
    }

    @SuppressWarnings("unused")
    public BusiException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public BusiException(String message, Throwable cause) {
        super(message, cause);
    }

    @SuppressWarnings("unused")
    public BusiException(Throwable cause) {
        super(cause);
    }
}
