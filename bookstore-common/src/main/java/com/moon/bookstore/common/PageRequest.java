package com.moon.bookstore.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求
 * @author yujiangtao
 * @date 2020/7/18 上午10:14
 */
@Data
public class PageRequest implements Serializable {

    /**
     * 分页页数
     */
    private int pageNo;

    /**
     * 每页记录
     */
    private int pageSize;
}
