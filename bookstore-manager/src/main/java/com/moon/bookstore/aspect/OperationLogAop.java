package com.moon.bookstore.aspect;

import com.moon.bookstore.api.entity.OperateLog;
import com.moon.bookstore.api.service.IOperateLogService;
import com.moon.bookstore.common.RestResponse;
import com.moon.bookstore.common.annotation.LogRecord;
import com.moon.bookstore.common.constant.BizType;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
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
public class OperationLogAop implements BeanFactoryAware {

    @Autowired
    private IOperateLogService operateLogService;

    private BeanFactory beanFactory;

    //@Pointcut("execution(* com.moon.bookstore.controller.*.*(..))")
    @Pointcut("@annotation(com.moon.bookstore.common.annotation.LogRecord)")
    public void operationLog() {

    }

    @Around("operationLog()")
    @SuppressWarnings("rawtypes")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        // 获取请求的方法
        Method method = point.getTarget().getClass().getMethod(signature.getName(), ((MethodSignature) signature).getParameterTypes());
        LogRecord annotation = method.getAnnotation(LogRecord.class);
        Object result = point.proceed();
        Object[] args = point.getArgs();

        // 创建解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        // 创建解析器上下文
        ParserContext context = new TemplateParserContext("#{", "}");

        // 创建表达式计算上下文
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        // 支持实例方法调用
        evaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
        // 返回结果
        if (result instanceof RestResponse) {
            evaluationContext.setVariable("_ret", ((RestResponse) result).getData());
        }
        // 入参
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                evaluationContext.setVariable("param" + i, args[i]);
            }
        }

        // 操作内容
        String operateContent = annotation.operateContent();
        Expression expression = parser.parseExpression(operateContent, context);
        String contentValue = expression.getValue(evaluationContext, String.class);

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
            // 读取 @ApiOperation
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if (apiOperation != null) {
                operateLog.setOperateName(apiOperation.value());
            } else {
                operateLog.setOperateName("自动填充");
            }
        }

        operateLog.setOperateContent(contentValue);

        if (result instanceof RestResponse) {
            RestResponse response = (RestResponse) result;
            if (response.isSuccess()) {
                operateLogService.addOperateLog(operateLog);
            }
        }
        return result;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
