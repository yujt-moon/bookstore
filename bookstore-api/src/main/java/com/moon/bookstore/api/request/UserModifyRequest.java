package com.moon.bookstore.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yujiangtao
 * @date 2021/10/27 上午10:30
 */
@Data
public class UserModifyRequest implements Serializable {

    private Long id;

    private String userName;

    private String realName;

    private String password;

    private String phone;

    private String email;

    private String avatar;

    private Integer status;
}
