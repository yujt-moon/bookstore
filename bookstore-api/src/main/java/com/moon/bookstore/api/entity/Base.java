package com.moon.bookstore.api.entity;

import com.moon.bookstore.common.annotation.CreateTime;
import com.moon.bookstore.common.annotation.UpdateTime;
import lombok.Data;

import java.util.Date;

/**
 * @author yujiangtao
 * @date 2021/12/16 下午5:31
 */
@Data
public class Base {
    /**
     * 创建时间
     */
    @CreateTime
    private Date createdTime;

    /**
     * 更新时间
     */
    @UpdateTime
    private Date updatedTime;
}
