package com.moon.bookstore.service.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author yujiangtao
 * @date 2021/12/16 下午7:40
 */
@Component
@Slf4j
public class AsyncTask {

    @Async
    public void doTask1() {
        long t1 = System.currentTimeMillis();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("doTask1 error: {}",e.getMessage(), e);
        }
        long t2 = System.currentTimeMillis();
        log.info("task1 cost {} ms", t2 - t1);
    }

    @Async
    public Future<String> doTask2() {
        long t1 = System.currentTimeMillis();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error("doTask2 error: {}", e.getMessage(), e);
        }
        long t2 = System.currentTimeMillis();
        log.info("task2 cost {} ms", t2 - t1);
        return new AsyncResult<String>("hello, async");
    }
}
