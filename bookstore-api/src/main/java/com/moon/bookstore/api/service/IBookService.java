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
     * @param books 书籍信息
     * @return 结果
     */
    boolean batchAdd(List<Book> books);

    /**
     * 查询书籍的分页信息
     * @param request 分页请求
     * @return 书籍列表
     */
    IPage<Book> page(BookPageRequest request);

    /**
     * 将书籍导入 es
     * @return 导入结果
     * @throws Exception 异常信息
     */
    boolean insertListIntoES() throws Exception;

    /**
     * es 中搜索书籍信息
     * @param request 搜索参数
     * @return 书籍信息
     */
    IPage<Book> search(BookPageRequest request);

    /**
     * 查询书籍信息
     * @param bookId 书籍id
     * @param categoryId 分类id（分库分表路由）
     * @return 书籍详情
     */
    Book getBookInfo(Long bookId, Long categoryId);

    /**
     * 获取分片的书籍信息
     * @return 书籍列表
     */
    List<Book> getShardingBooks();

    /**
     * 新增书籍信息
     * @param request 书籍信息
     * @return 新增结果
     */
    Boolean addBook(BookAddRequest request);
}
