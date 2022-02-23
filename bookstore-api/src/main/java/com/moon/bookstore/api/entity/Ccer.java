package com.moon.bookstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 国家核证自愿减排量
 *
 * @author yujiangtao
 * @date 2021/8/20 11:44
 */
@Data
@TableName("t_ccer")
public class Ccer {

    /**
     * 主键
     */
    private Long id;

    /**
     * 交易所id
     */
    private Long exchangeId;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 成交量
     */
    private Long tradeVolume;

    /**
     * 成交均价
     */
    private BigDecimal averagePrice;

    /**
     * 成交额
     */
    private BigDecimal tradeFee;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记位置
     */
    private Boolean deleted;
}
