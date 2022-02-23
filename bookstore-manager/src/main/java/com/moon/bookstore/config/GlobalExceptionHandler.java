package com.moon.bookstore.config;

import com.moon.bookstore.common.exception.BusiException;
import com.moon.bookstore.common.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 * @author yujiangtao
 * @date 2021/7/23 下午3:33
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常处理器
     * @param request 请求
     * @param e 异常信息
     * @return 统一封装响应
     */
    @ExceptionHandler(value = BusiException.class)
    @ResponseBody
    @SuppressWarnings({"rawtypes", "unused"})
    public RestResponse busiExceptionHandler(HttpServletRequest request, BusiException e) {
        log.error("业务异常: {}", e.getMessage(), e);
        return RestResponse.fail(e.getMessage());
    }

    /**
     * 全局异常处理类
     * @param request 请求
     * @param e 异常信息
     * @return 统一封装响应
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @SuppressWarnings({"rawtypes", "unused"})
    public RestResponse exceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        return RestResponse.fail("系统异常");
    }

    /**
     * 参数异常处理类
     * @param e 异常信息
     * @return 统一封装响应
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @SuppressWarnings({"rawtypes", "unused"})
    public RestResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        StringBuffer errorInfo = new StringBuffer();
        if(bindingResult.hasFieldErrors()) {
            bindingResult.getFieldErrors().forEach(error -> errorInfo.append(error.getDefaultMessage()).append(" "));
            errorInfo.delete(errorInfo.length() - 1, errorInfo.length());
        }
        log.error("参数异常：{}", errorInfo.toString());
        return RestResponse.fail(errorInfo.toString());
    }
}
