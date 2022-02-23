package com.moon.bookstore.api.request;


import com.moon.bookstore.common.PageRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 书籍分页请求
 * @author yujiangtao
 * @date 2020/7/18 上午10:16
 */
@Data
public class BookPageRequest extends PageRequest {

    @NotNull(message = "关键字不能为空")
    private String keyword;
}
