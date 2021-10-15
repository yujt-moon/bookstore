package com.moon.bookstore.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moon.bookstore.api.entity.Book;
import com.moon.bookstore.api.request.BookAddRequest;
import com.moon.bookstore.api.request.BookDelRequest;
import com.moon.bookstore.api.request.BookPageRequest;
import com.moon.bookstore.api.service.IBookService;
import com.moon.bookstore.common.RestResponse;
import com.moon.bookstore.common.annotation.LogRecord;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yujiangtao
 * @date 2020/7/14 下午8:58
 */
@RestController
@RequestMapping("/api/book")
@Slf4j
public class BookController {

    @Autowired
    private IBookService bookService;

    @PostMapping("/page")
    public RestResponse<IPage<Book>> page(@RequestBody BookPageRequest request) {
        log.info("查询书籍分页请求参数：{}", JSON.toJSONString(request));
        return RestResponse.ok(bookService.page(request));
    }

    @GetMapping("/insertAllEs")
    @SuppressWarnings("rawtypes")
    public RestResponse insertAllEs() {
        boolean isSuccess = false;
        try {
            isSuccess = bookService.insertListIntoES();
        } catch (Exception e) {
            log.error("全量导入es出错：", e);
        }
        return RestResponse.auto(isSuccess);
    }

    @PostMapping("/search")
    public RestResponse<IPage<Book>> search(@RequestBody @Valid BookPageRequest request) {
        IPage<Book> page = bookService.search(request);
        return RestResponse.ok(page);
    }

    @GetMapping("/getOne/{bookId}/{categoryId}")
    public RestResponse<Book> getOne(@PathVariable Long bookId, @PathVariable Long categoryId) {
        return RestResponse.ok(bookService.getBookInfo(bookId, categoryId));
    }

    @PostMapping("/add")
    @ApiOperation("新增书籍")
    @LogRecord(bizCode = "book", operateContent = "新增#{#param0.name}，结果是#{#_ret?'成功':'失败'}")
    public RestResponse<Boolean> insertBook(@RequestBody @Valid BookAddRequest request) {
        log.info(">>>> 新增书籍请求参数:{}", JSON.toJSONString(request));
        return RestResponse.ok(bookService.addBook(request));
    }

    @PostMapping("/del")
    @ApiOperation("删除书籍")
    @LogRecord(bizCode = "book", operateContent = "#{@bookService.getBookInfo(#{param0}).name}")
    public RestResponse<Boolean> delBook(@RequestBody @Valid BookDelRequest request) {
        return RestResponse.ok(bookService.removeById(request.getId()));
    }
}
