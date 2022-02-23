package com.moon.bookstore.api.vo;

import com.moon.bookstore.common.annotation.DecimalSerializeOpt;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yujiangtao
 * @date 2021/8/30 下午9:53
 */
@Data
@AllArgsConstructor
public class DecimalVo implements Serializable {

    private int id;

    @DecimalSerializeOpt(scale = 4, roundingMode = BigDecimal.ROUND_FLOOR)
    private BigDecimal highPrice;

    @DecimalSerializeOpt(pattern = "##.00%")
    private BigDecimal lowPrice;

    private BigDecimal withoutOpt;

    @DecimalSerializeOpt(scale = 2)
    private BigDecimal nullValue;
}
