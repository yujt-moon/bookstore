package com.moon.bookstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 全国碳排放配额信息
 *
 * @author yujiangtao
 * @date 2021/8/18 14:53
 */
@Data
@TableName("t_cea")
public class Cea {

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 最高价
     */
    private BigDecimal maxPrice;

    /**
     * 最低价
     */
    private BigDecimal minPrice;

    /**
     * 开盘价
     */
    private BigDecimal openingPrice;

    /**
     * 收盘价
     */
    private BigDecimal closingPrice;

    /**
     * 涨跌幅
     */
    private BigDecimal priceLimit;

    /**
     * 挂牌均价
     */
    private BigDecimal listingAveragePrice;

    /**
     * 挂牌成交量
     */
    private Long listingTradeVolume;

    /**
     * 挂牌成交额
     */
    private BigDecimal listingTradeFee;

    /**
     * 大宗均价
     */
    private BigDecimal largeAveragePrice;

    /**
     * 大宗成交量
     */
    private Long largeTradeVolume;

    /**
     * 大宗成交额
     */
    private BigDecimal largeTradeFee;

    /**
     * 累计成交量
     */
    private Long accumulateVolume;

    /**
     * 累计成交额
     */
    private BigDecimal accumulateFee;
}
