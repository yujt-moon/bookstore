package com.moon.bookstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 *
 * 章节信息
 *
 * @author yujiangtao
 * @date 2021/1/11 下午10:58
 */
@TableName("chapter")
@Data
public class Chapter {

    private int id;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 章节序号
     */
    private int no;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 内容
     */
    private String content;

    /**
     * 书籍id
     */
    private int bookId;
}
