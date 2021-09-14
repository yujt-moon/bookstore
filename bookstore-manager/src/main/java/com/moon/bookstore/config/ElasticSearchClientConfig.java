package com.moon.bookstore.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * elasticsearch 配置文件
 *
 * @author yujiangtao
 * @date 2020/9/21 下午4:24
 */
@Configuration
public class ElasticSearchClientConfig {

    // spring <beans id="restHighLevelClient" class="RestHighLevelClient">
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        return client;
    }
}
