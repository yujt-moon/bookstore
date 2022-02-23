package com.moon.bookstore.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yujiangtao
 * @date 2021/10/26 下午5:48
 */
@Data
public class UserAddRequest implements Serializable {

    private String userName;

    private String realName;

    private String password;

    private String phone;

    private String email;

    private String avatar;

    private Integer status;
}
