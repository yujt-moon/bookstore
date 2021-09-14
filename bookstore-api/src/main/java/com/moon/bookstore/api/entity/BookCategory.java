package com.moon.bookstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 数据库对应的实体
 * @author yujiangtao
 * @date 2020/7/15 下午3:37
 */
@TableName("book_category")
@Data
public class BookCategory {

    private int id;

    private String categoryName;

    private String categoryIcon;
}
