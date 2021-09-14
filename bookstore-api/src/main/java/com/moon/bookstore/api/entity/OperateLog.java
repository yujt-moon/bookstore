package com.moon.bookstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 * @author yujiangtao
 * @date 2021/9/14 下午5:42
 */
@TableName("t_operate_log")
@Data
public class OperateLog implements Serializable {

    private Long id;

    private String bizCode;

    private Integer bizType;

    private String operateName;

    private String operateContent;

    private String operateUser;

    private Date createTime;

    private String createBy;
}
