package com.moon.bookstore.service.spider;

import com.moon.bookstore.api.entity.Book;
import com.moon.bookstore.api.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/7/17 下午12:29
 */
@Component
public class BookPipeline implements Pipeline {

    @Autowired
    private IBookService bookService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Book> books = (List<Book>) resultItems.get("books");
        bookService.batchAdd(books);
    }
}
