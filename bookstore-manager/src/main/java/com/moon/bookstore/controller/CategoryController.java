package com.moon.bookstore.controller;

import com.google.common.collect.Lists;
import com.moon.bookstore.common.RestResponse;
import com.moon.bookstore.api.entity.BookCategory;
import com.moon.bookstore.api.service.IBookCategoryService;
import com.moon.bookstore.api.vo.BookCategoryVo;
import com.moon.bookstore.converter.output.BookCategoryOutputConverter;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 小说分类
 * @author yujiangtao
 * @date 2020/7/15 下午3:24
 */
@Slf4j
@RestController
@RequestMapping("/api/category")
@SuppressWarnings("unused")
public class CategoryController {

    @Autowired
    private IBookCategoryService bookCategoryService;

    @Autowired
    private BookCategoryOutputConverter outputConverter;

    @RequestMapping("/list")
    @ApiOperation("查询分类信息")
    public RestResponse<List<BookCategoryVo>> getCategories() {
        List<BookCategory> categories = bookCategoryService.getCategories();
        List<BookCategoryVo> categoryVos = Lists.newArrayList();
        Optional.ofNullable(categories).ifPresent(list -> list.forEach(c -> {
            BookCategoryVo bookCategoryVo = outputConverter.entity2Vo(c);
            categoryVos.add(bookCategoryVo);
        }));
        return RestResponse.ok(categoryVos);
    }

    @PostMapping("/add")
    @ApiOperation("新增分类")
    public RestResponse addCategory(@RequestBody BookCategory bookCategory) {
        boolean success = bookCategoryService.addCategory(bookCategory);
        return RestResponse.auto(success);
    }

    @GetMapping("/del/{id}")
    @ApiOperation("删除分类")
    public RestResponse delCategory(@PathVariable int id) {
        return RestResponse.auto(bookCategoryService.delCategory(id));
    }
}
