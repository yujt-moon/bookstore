package com.moon.bookstore.common.annotation;

import com.moon.bookstore.common.constant.BizType;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * @author yujiangtao
 * @date 2021/9/14 下午6:15
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRecord {

    /**
     * 业务编码，查询不同的业务时需要
     * @return bizCode
     */
    String bizCode();

    /**
     * 业务类型（1：添加 2：修改 3：删除）
     * @return bizType
     */
    BizType bizType() default BizType.UNDEFINED;

    /**
     * 操作名称
     * @return operateName
     */
    String operateName() default "";

    /**
     * 操作内容
     * @return operateContent
     */
    String operateContent() default "";
}
