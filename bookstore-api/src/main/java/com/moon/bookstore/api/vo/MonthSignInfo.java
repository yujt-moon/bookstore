package com.moon.bookstore.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户每月签到情况
 * @author yujiangtao
 * @date 2021/7/20 下午5:57
 */
@ApiModel("用户每月签到情况")
@Data
public class MonthSignInfo implements Serializable {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("进行签到的日期")
    private List<Integer> signDates;

    @ApiModelProperty("连续签到天数")
    private Integer signCount;
}
