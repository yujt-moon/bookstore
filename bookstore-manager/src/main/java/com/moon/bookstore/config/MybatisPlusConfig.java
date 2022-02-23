package com.moon.bookstore.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yujiangtao
 * @date 2020/7/17 下午8:03
 */
@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MybatisPlusConfig {

    /**
     * 配置分页插件
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 配置 mybatis 插件
     * @return ConfigurationCustomizer
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        /*return new ConfigurationCustomizer() {
            @Override
            public void customize(MybatisConfiguration configuration) {
                MyInterceptor myInterceptor = new MyInterceptor();
                configuration.addInterceptor(myInterceptor);
            }
        };*/
        return configuration -> {
            FieldInjectInterceptor fieldInjectInterceptor = new FieldInjectInterceptor();
            configuration.addInterceptor(fieldInjectInterceptor);
        };
    }
}
