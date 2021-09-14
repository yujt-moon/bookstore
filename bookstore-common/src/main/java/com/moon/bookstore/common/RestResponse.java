package com.moon.bookstore.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 响应包装
 * @author yujiangtao
 * @date 2020/7/15 下午5:03
 */
@Getter
@Setter
public class RestResponse<T> implements Serializable {

    /**
     * 业务编码
     */
    private String code;

    /**
     * 业务信息（不出错的时候为空）
     */
    private String msg;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 数据存放
     */
    private T data;

    public static <T> RestResponse<T> ok(T data) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.code = "0000";
        restResponse.success = true;
        restResponse.msg = "操作成功";
        restResponse.data = data;
        return restResponse;
    }

    public static <T> RestResponse<T> ok() {
        return RestResponse.ok(null);
    }

    public static <T> RestResponse<T> fail() {
        return RestResponse.fail("9999", "操作失败");
    }

    public static <T> RestResponse<T> fail(String code, String msg) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.code = code;
        restResponse.success = false;
        restResponse.msg = msg;
        return restResponse;
    }

    public static <T> RestResponse<T> fail(String msg) {
        return RestResponse.fail("9999", msg);
    }

    public static <T> RestResponse<T> auto(boolean success) {
        return success ? ok() : fail();
    }
}
