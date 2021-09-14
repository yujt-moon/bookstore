package com.moon.bookstore.api.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yujiangtao
 * @date 2021/7/15 下午11:35
 */
@Data
public class BookAddRequest {

    private Integer categoryId;

    @NotNull(message = "书籍名称")
    private String name;
}
