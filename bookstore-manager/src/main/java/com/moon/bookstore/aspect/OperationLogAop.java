package com.moon.bookstore.aspect;

import com.alibaba.fastjson.JSON;
import com.moon.bookstore.api.entity.OperateLog;
import com.moon.bookstore.api.service.IOperateLogService;
import com.moon.bookstore.common.RestResponse;
import com.moon.bookstore.common.annotation.OperateLogOpt;
import com.moon.bookstore.common.constant.BizType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author yujiangtao
 * @date 2021/9/14 下午3:42
 */
@Slf4j
@Aspect
@Component
public class OperationLogAop {

    @Autowired
    private IOperateLogService operateLogService;

    @Pointcut("execution(* com.moon.bookstore.controller.*.*(..))")
    public void operationLog() {

    }

    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        // 获取请求的方法
        Method method = point.getTarget().getClass().getMethod(signature.getName(), ((MethodSignature) signature).getParameterTypes());
        OperateLogOpt annotation = method.getAnnotation(OperateLogOpt.class);
        Object result = point.proceed();
        // 判断是否存在对应的注解
        if (annotation != null)  {
            OperateLog operateLog = new OperateLog();
            operateLog.setBizCode(annotation.bizCode());
            operateLog.setCreateTime(new Date());
            operateLog.setCreateBy("System");
            if (annotation.bizType() == BizType.UNDEFINED) {
                String methodName = method.getName();
                if (methodName.contains("save") || methodName.contains("insert") || methodName.contains("add")) {
                    operateLog.setBizType(BizType.ADD.getCode());
                } else if (methodName.contains("update") || methodName.contains("modify")) {
                    operateLog.setBizType(BizType.UPDATE.getCode());
                } else if (methodName.contains("del")) {
                    operateLog.setBizType(BizType.DELETE.getCode());
                } else {
                    operateLog.setBizType(BizType.UNDEFINED.getCode());
                }
            } else {
                operateLog.setBizType(annotation.bizType().getCode());
            }
            if (StringUtils.isNotBlank(annotation.operateName())) {
                operateLog.setOperateName(annotation.operateName());
            } else {
                operateLog.setOperateName("自动填充");
            }
            Object[] args = point.getArgs();
            operateLog.setOperateContent(JSON.toJSONString(args));

            if (result instanceof RestResponse) {
                RestResponse response = (RestResponse) result;
                if (response.isSuccess()) {
                    operateLogService.addOperateLog(operateLog);
                }
            }
        }
        return result;
    }
}
