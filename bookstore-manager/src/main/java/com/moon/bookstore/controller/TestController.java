package com.moon.bookstore.controller;

import com.moon.bookstore.common.RestResponse;
import com.moon.bookstore.api.vo.DecimalVo;
import com.moon.bookstore.service.task.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author yujiangtao
 * @date 2021/8/30 下午10:09
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AsyncTask asyncTask;

    @GetMapping("/serialize")
    public RestResponse<DecimalVo> serialDecimal() {
        return RestResponse.ok(new DecimalVo(1, new BigDecimal(12.33547), new BigDecimal(4.14255), new BigDecimal(12.3454), null));
    }

    @GetMapping("/async")
    public RestResponse<String> asyncTask() {
        asyncTask.doTask1();
        Future<String> future = asyncTask.doTask2();
        String result = "";
        while (!future.isDone())
            ;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("doTask2 error: {}", e.getMessage(), e);
        }
        return RestResponse.ok(result);
    }
}
