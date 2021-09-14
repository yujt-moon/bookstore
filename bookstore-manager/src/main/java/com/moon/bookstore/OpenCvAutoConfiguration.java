package com.moon.bookstore;

import com.moon.bookstore.service.OpenCvLibConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author yujiangtao
 * @date 2021/8/18 下午8:21
 */
@Configuration
public class OpenCvAutoConfiguration {

    @Autowired
    private OpenCvLibConfig openCvLibConfig;

    @PostConstruct
    public void init() {
        System.load(openCvLibConfig.getPath());
    }
}
