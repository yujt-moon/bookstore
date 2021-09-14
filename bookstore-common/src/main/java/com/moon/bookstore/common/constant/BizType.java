package com.moon.bookstore.common.constant;

/**
 * @author yujiangtao
 * @date 2021/9/14 下午6:18
 */
public enum BizType {
    UNDEFINED(0),
    ADD(1),
    UPDATE(2),
    DELETE(3);

    private int code;

    BizType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
