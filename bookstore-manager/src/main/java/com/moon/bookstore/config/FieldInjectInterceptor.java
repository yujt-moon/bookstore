package com.moon.bookstore.config;

import com.moon.bookstore.common.annotation.CreateTime;
import com.moon.bookstore.common.annotation.UpdateTime;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 字段填充拦截器
 * @author yujiangtao
 * @date 2021/12/15 下午4:34
 */
@Intercepts({
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class FieldInjectInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object object = invocation.getArgs()[1];

        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Date now = new Date();
        List<Field> declaredFields = new ArrayList<>();
        // 是否是批量操作
        boolean isBatch = false;
        if (object instanceof List) {
            isBatch = true;
            List<?> list = ((List<?>) object);
            Class<?> clazz = list.get(0).getClass();
            do {
                declaredFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
                clazz = clazz.getSuperclass();
            } while (clazz != null);
        } else {
            Class<?> clazz = object.getClass();
            do {
                declaredFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
                clazz = clazz.getSuperclass();
            } while (clazz != null);
        }
        // 新增操作
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(CreateTime.class) || field.isAnnotationPresent(UpdateTime.class)) {
                    fillField(field, object, isBatch, now);
                }
            }
        }
        // 修改操作
        else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(UpdateTime.class)) {
                    fillField(field, object, isBatch, now);
                }
            }
        }
        return invocation.proceed();
    }

    /**
     * 填充字段
     * @param field 字段
     * @param object 对象
     * @param isBatch 是否是批量
     * @param value 当前时间
     * @throws Throwable 异常
     */
    private void fillField(Field field, Object object, boolean isBatch, Object value) throws Throwable {
        field.setAccessible(true);
        if (isBatch) {
            List<?> list = ((List<?>) object);
            for (Object o : list) {
                field.set(o, value);
            }
        } else {
            field.set(object, value);
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
