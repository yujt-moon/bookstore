package com.moon.bookstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yujiangtao
 * @date 2022/2/22 下午5:11
 */
@TableName("t_pilot_cea")
@Builder
@Data
public class PilotCea implements Serializable {

    @TableField("trade_date")
    private Date tradeDate;

    @TableField("stock_type")
    private String stockType;

    @TableField("exchange_code")
    private String exchangeCode;

    @TableField("opening_price")
    private Double openingPrice;

    @TableField("max_price")
    private Double maxPrice;

    @TableField("min_price")
    private Double minPrice;

    @TableField("average_price")
    private Double averagePrice;

    @TableField("closing_price")
    private Double closingPrice;

    @TableField("trade_volume")
    private Long tradeVolume;

    @TableField("trade_fee")
    private Double tradeFee;
}
