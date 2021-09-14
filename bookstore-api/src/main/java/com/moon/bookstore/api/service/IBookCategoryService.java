package com.moon.bookstore.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.bookstore.api.entity.BookCategory;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/7/15 下午4:22
 */
public interface IBookCategoryService extends IService<BookCategory> {

    /**
     * 查询分类信息列表
     * @return
     */
    List<BookCategory> getCategories();

    /**
     * 新增分类
     * @param category
     * @return
     */
    boolean addCategory(BookCategory category);

    /**
     * 删除分类
     * @param id
     * @return
     */
    boolean delCategory(int id);
}
