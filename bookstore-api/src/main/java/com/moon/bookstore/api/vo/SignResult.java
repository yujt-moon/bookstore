package com.moon.bookstore.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 签到结果
 * @author yujiangtao
 * @date 2021/7/20 下午1:56
 */
@ApiModel("签到结果")
@Data
@Builder
public class SignResult implements Serializable {

    @ApiModelProperty("连续签到天数")
    private Integer signCount;

    @ApiModelProperty("签到获取的守护星")
    private Integer starCount;
}
