package com.moon.bookstore.api.vo;

import lombok.Data;

/**
 * @author yujiangtao
 * @date 2020/7/17 下午7:50
 */
@Data
public class BookVo {

    /**
     * 主键
     */
    private int id;

    /**
     * 书名
     */
    private String name;

    /**
     * 分类id
     */
    private String categoryId;

    /**
     * 子分类id
     */
    private String subCategoryId;

    /**
     * 作者
     */
    private String authorId;

    /**
     * 状态
     */
    private String status;

    /**
     * 封面图片
     */
    private String bookCover;

    /**
     * 简介
     */
    private String intro;
}
