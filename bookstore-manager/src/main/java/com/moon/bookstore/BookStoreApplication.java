package com.moon.bookstore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author yujiangtao
 * @date 2020/7/14 下午8:30
 */
@Slf4j
// 监控注解
//@EnableMetrics
@EnableElasticsearchRepositories
// springboot 启动类注解
@SpringBootApplication(scanBasePackages = {"com.moon.bookstore"})
public class BookStoreApplication {

    public static void main(String[] args) {
        // 通过 springboot 内部的类加载机制对字节码文件进行加载
        ConfigurableApplicationContext context = SpringApplication.run(BookStoreApplication.class, args);
        //ScheduledReporter reporter = context.getBean(ScheduledReporter.class);
        //reporter.start(1, TimeUnit.SECONDS);
        log.info("Swagger-UI: http://127.0.0.1:9999/swagger-ui.html");
    }
}
