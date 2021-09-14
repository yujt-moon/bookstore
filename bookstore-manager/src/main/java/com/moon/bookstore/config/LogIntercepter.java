package com.moon.bookstore.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 打印入参
 *
 * @author yujiangtao
 * @date 2021/5/9 下午10:55
 */
@Component
@Slf4j
public class LogIntercepter extends HandlerInterceptorAdapter {

    //@Autowired
    //private Meter meter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) request;
//        String contentType = request.getContentType();
//        if(StringUtils.isNotBlank(contentType)) {
//            contentType = contentType.split(";")[0];
//        }
//        if("application/x-www-form-urlencoded".equals(contentType)) {
//            Enumeration<String> parameterNames = request.getParameterNames();
//            while (parameterNames.hasMoreElements()) {
//                String parameterName = parameterNames.nextElement();
//                log.info(parameterName + ":" + request.getParameter(parameterName));
//            }
//        } else if("text/plain".equals(contentType) || "application/json".equals(contentType)) {
//            BufferedReader reader = wrapper.getReader();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                log.info("请求入参：{}", line);
//            }
//        }

        log.info(">>>> requestUri = {}, requestParam = {}", request.getRequestURI(), JSON.toJSONString(request.getParameterMap()));
        //meter.mark();

        return super.preHandle(request, response, handler);
    }
}
