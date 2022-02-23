package com.moon.bookstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户签到
 * @author yujiangtao
 * @date 2021/7/20 上午11:07
 */
@TableName("user_sign")
@Data
public class UserSign implements Serializable {

    @TableId
    private Long id;

    // 用户id
    private String userKey;

    // 签到月份
    private String signMonth;

    // 签到记录
    private Integer signRecord;

    // 连续签到天数
    private Integer signCount;

    // 上次签到日期
    private Date lastSignDate;

    // 补签次数
    private Integer replenishSign;
}
