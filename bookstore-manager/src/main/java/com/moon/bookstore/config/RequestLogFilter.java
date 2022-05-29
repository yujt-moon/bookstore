package com.moon.bookstore.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 日志记录 filter
 * @author yujiangtao
 * @date 2021/5/13 上午12:52
 */
@Slf4j
@Order(1)
@Component
@WebFilter(filterName = "logFilter", urlPatterns = {"/"})
public class RequestLogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 如果是文件上传，则不打印日志
        if (ServletFileUpload.isMultipartContent(request)) {
            filterChain.doFilter(request, response);
        } else {
            HttpServletRequest wrapper = new RequestWrapper(request);
            logParam(wrapper);
            filterChain.doFilter(wrapper, response);
        }
    }

    /**
     * 打印请求参数
     * @param request 请求
     * @throws IOException io异常
     */
    private void logParam(HttpServletRequest request) throws IOException {
        String url = request.getRequestURL().toString();
        log.info("请求链接：{}", url);
        String contentType = request.getContentType();
        if(StringUtils.isNotBlank(contentType)) {
            contentType = contentType.split(";")[0];
        }
        if("application/x-www-form-urlencoded".equals(contentType)) {
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String parameterName = parameterNames.nextElement();
                log.info(parameterName + ":" + request.getParameter(parameterName));
            }
        } else if("text/plain".equals(contentType) || "application/json".equals(contentType)) {
            BufferedReader reader = request.getReader();
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            log.info("请求入参：{}", sb.toString());
        } else {
            if ("GET".equalsIgnoreCase(request.getMethod())) {
                String queryString = request.getQueryString();
                if (StringUtils.isNotBlank(queryString)) {
                    log.info("请求入参：{}", queryString);
                }
            }
        }
    }
}
