package com.moon.bookstore.config;//package com.moon.bookstore.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
//import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author yujiangtao
// * @date 2021/10/26 上午10:42
// */
//@EnableWebSecurity(debug = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    public WebSecurityConfig() {
//
//    }
//
//    // 配置用户
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//    }
//
//    // 忽略静态资源，不会参与认证
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//
//    }
//
//    // 配置资源权限规则
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/api/user/page")
//                .permitAll()
//                .anyRequest()
//                .authenticated();
//    }
//
//    // 密码编码器
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // return new BCryptPasswordEncoder();
//        String idForDefault = "bcrypt"; // 默认编码
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put(idForDefault, new BCryptPasswordEncoder());
//        encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
//        return new DelegatingPasswordEncoder(idForDefault, encoders);
//    }
//}
