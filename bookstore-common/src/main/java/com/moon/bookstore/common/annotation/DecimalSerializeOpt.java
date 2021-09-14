package com.moon.bookstore.common.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.moon.bookstore.common.serialize.DecimalSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

/**
 * 自定义 BigDecimal 序列化工具
 * @author yujiangtao
 * @date 2021/8/30 下午9:56
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = DecimalSerializer.class)
@JacksonAnnotationsInside
public @interface DecimalSerializeOpt {

    /**
     * 优先级高于 scale
     * @return format 的 pattern
     */
    String pattern() default "";

    /**
     * 使用 BigDecimal 中的 RoundingMode的值，默认使用四舍五入
     * @return 舍入方式
     */
    int roundingMode() default BigDecimal.ROUND_HALF_UP;

    /**
     * 小数位数
     * @return 小数位数
     */
    int scale() default 2;
}
