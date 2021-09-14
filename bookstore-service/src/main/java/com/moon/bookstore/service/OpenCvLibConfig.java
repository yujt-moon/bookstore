package com.moon.bookstore.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * opencv 动态库配置
 * @author yujiangtao
 * @date 2021/8/13 上午11:38
 */
@Data
@Component
@ConfigurationProperties(prefix = "opencv.lib")
public class OpenCvLibConfig {
    private String path;
}
