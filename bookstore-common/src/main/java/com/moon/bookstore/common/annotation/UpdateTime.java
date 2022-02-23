package com.moon.bookstore.common.annotation;

import java.lang.annotation.*;

/**
 * @author yujiangtao
 * @date 2021/12/15 下午5:50
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdateTime {
}
