package com.moon.bookstore.converter.output;

import com.moon.bookstore.api.entity.BookCategory;
import com.moon.bookstore.api.vo.BookCategoryVo;
import org.mapstruct.Mapper;

/**
 * @author yujiangtao
 * @date 2020/7/15 下午4:54
 */
@Mapper(componentModel = "spring")
public interface BookCategoryOutputConverter {

    /**
     * 将 entity 转为 vo
     * @param bookCategory
     * @return
     */
    BookCategoryVo entity2Vo(BookCategory bookCategory);
}
