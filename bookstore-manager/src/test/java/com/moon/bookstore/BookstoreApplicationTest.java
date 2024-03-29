package com.moon.bookstore;

import com.moon.bookstore.api.entity.Book;
import com.moon.bookstore.api.request.BookAddRequest;
import com.moon.bookstore.api.service.IBookService;
import com.moon.bookstore.service.spider.CeaProcessor;
import com.moon.bookstore.service.spider.PilotCeaPipeline;
import com.moon.bookstore.service.spider.PilotCeaProcessor;
import com.moon.bookstore.service.task.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;

/**
 * @author yujiangtao
 * @date 2021/12/16 上午10:19
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTest {

    @Autowired
    private IBookService bookService;

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private PilotCeaPipeline pilotCeaPipeline;

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    @Test
    public void addBook() {
        BookAddRequest request = new BookAddRequest();
        request.setName("测试新增");
        bookService.addBook(request);
    }

    @Test
    public void batchAddBooks() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Book book = new Book();
            book.setName("批量" + i);
            books.add(book);
        }
        bookService.batchAdd(books);
    }

    @Test
    public void testAsync() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        asyncTask.doTask1();
        asyncTask.doTask2();
        Thread.sleep(1000);
        long t2 = System.currentTimeMillis();
        log.info("main cost {} ms", t2-t1);
        while (true)
            ;
    }

    @Test
    public void testCeaSpider() {
        Spider.create(new CeaProcessor())
                //.addUrl("https://www.cneeex.com/cneeex/catalog/15317/pc/index_6.shtml")
                .addUrl("https://www.cneeex.com/qgtpfqjy/mrgk/2022n/")
                .addPipeline(new ConsolePipeline())
                //.addPipeline(new CeaPipeline())
                .thread(1)
                .start();
        while (true);
    }

    @Test
    public void testPilotCeaSpider() {
        Spider.create(new PilotCeaProcessor())
                .addUrl("http://www.cerx.cn/dailynewsCN/index.htm")
                .addPipeline(new ConsolePipeline())
                .addPipeline(pilotCeaPipeline)
                .thread(1)
                .start();
        while (true)
            ;
    }

    @Test
    public void contextLoads() throws Exception {
        // 一共 10 批任务
        for (int i = 0; i < 10; i++) {
            // 每执行一批任务
            doOnceTasks();
            System.out.println("-------------------------------" + i);
        }
    }

    private void doOnceTasks() {
        List<Future> futureList = new ArrayList<>(16);
        for (int i = 0; i < 15; i++) {
            Future future = executor.submit(() -> {
                // 随机睡 0-5秒
                int sec = new Double(Math.random() * 5).intValue();
                LockSupport.parkNanos(sec * 1000 * 1000 * 1000);
                System.out.println(Thread.currentThread().getName() + " end");
            });
            futureList.add(future);
        }

        // 等待所有任务执行结束
        for (Future future : futureList) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
