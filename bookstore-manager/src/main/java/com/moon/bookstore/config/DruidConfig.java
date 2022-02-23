package com.moon.bookstore.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * druid 监控配置
 *
 * @author yujiangtao
 * @date 2021/7/13 下午3:43
 */
@Configuration
public class DruidConfig {

    /**
     * 注册 druid servlet
     * @return ServletRegistrationBean
     */
    @Bean
    @SuppressWarnings("rawtypes")
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(
                new StatViewServlet(), "/druid/*");
        // 白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1,10.1.1.1");
        // 黑名单
        // servletRegistrationBean.addInitParameter("deny", "192.168.1.200");
        // 用户名
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        // 密码
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        // 是否可以重置数据源
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }
}
