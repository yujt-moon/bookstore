package com.moon.bookstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.moon.bookstore.common.annotation.CreateTime;
import com.moon.bookstore.common.annotation.UpdateTime;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yujiangtao
 * @date 2021/10/26 下午2:03
 */
@Data
public class BasePo implements Serializable {

    @TableId
    /**
     * 解决 Long 类型到前端 js 丢失精度
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    @CreateTime
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    @UpdateTime
    private Date updatedTime;
}
