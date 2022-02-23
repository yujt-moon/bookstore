package com.moon.bookstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author yujiangtao
 * @date 2020/7/17 上午11:31
 */
@TableName("book")
@Data
public class Book extends Base {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 书名
     * ik_max_word: 会将文本做最细粒度的拆分，比如会将“中华人民共和国国歌”拆分为
     * “中华人民共和国,中华人民,中华,华人,人民共和国,人民,人,民,共和国,共和,和,国国,国歌”，会穷尽各种可能的组合；
     * ik_smart: 会做最粗粒度的拆分，比如会将“中华人民共和国国歌”拆分为“中华人民共和国,国歌”。
     */
    private String name;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 分类名称
     */
    @TableField(exist = false)
    private String categoryName;

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
