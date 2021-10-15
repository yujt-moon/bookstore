package com.moon.bookstore.api.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yujiangtao
 * @date 2021/10/12 上午10:36
 */
@Data
public class BookDelRequest implements Serializable {

    @NotNull(message = "id不能为空")
    private Long id;
}
