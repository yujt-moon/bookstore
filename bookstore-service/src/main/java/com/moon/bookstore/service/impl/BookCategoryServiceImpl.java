package com.moon.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moon.bookstore.api.entity.BookCategory;
import com.moon.bookstore.api.service.IBookCategoryService;
import com.moon.bookstore.service.mapper.BookCategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/7/15 下午4:26
 */
@Service
public class BookCategoryServiceImpl extends ServiceImpl<BookCategoryMapper, BookCategory> implements IBookCategoryService {

    @Override
    public List<BookCategory> getCategories() {
        return baseMapper.selectList(new QueryWrapper());
    }

    @Override
    public boolean addCategory(BookCategory category) {
        return baseMapper.insert(category) > 0;
    }

    @Override
    public boolean delCategory(int id) {
        return baseMapper.deleteById(id) > 0;
    }
}
