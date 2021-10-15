package com.moon.bookstore.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.bookstore.api.entity.Book;
import com.moon.bookstore.api.request.BookAddRequest;
import com.moon.bookstore.api.request.BookPageRequest;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/7/17 下午2:17
 */
public interface IBookService extends IService<Book> {

    /**
     * 批量新增
     * @param books
     * @return
     */
    boolean batchAdd(List<Book> books);

    /**
     * 查询书籍的分页信息
     * @param request
     * @return
     */
    IPage<Book> page(BookPageRequest request);

    boolean insertListIntoES() throws Exception;

    IPage<Book> search(BookPageRequest request);

    Book getBookInfo(Long bookId, Long categoryId);

    /**
     * 获取分片的书籍信息
     * @return
     */
    List<Book> getShardingBooks();

    /**
     * 新增书籍信息
     * @param request
     * @return
     */
    Boolean addBook(BookAddRequest request);
}
