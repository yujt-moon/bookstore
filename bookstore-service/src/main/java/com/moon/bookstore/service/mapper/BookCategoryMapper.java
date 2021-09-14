package com.moon.bookstore.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moon.bookstore.api.entity.BookCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yujiangtao
 * @date 2020/7/15 下午4:42
 */
@Mapper
public interface BookCategoryMapper extends BaseMapper<BookCategory> {

}
