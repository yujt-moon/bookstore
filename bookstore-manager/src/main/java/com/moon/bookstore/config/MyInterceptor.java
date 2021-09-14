package com.moon.bookstore.config;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * 自定义mybatis 拦截器
 *
 * @author yujiangtao
 * @date 2021/2/28 上午10:24
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement =  (MappedStatement)args[0];
        Object object = args[1];

        Object target = invocation.getTarget();
        Field delegate = target.getClass().getDeclaredField("delegate");
        delegate.setAccessible(true);
        Executor executor = (Executor) delegate.get(target);
        // TODO


        BoundSql boundSql = mappedStatement.getBoundSql(object);
        String sql = boundSql.getSql();
        sql += " where 1=1 and category_name like %奇%";
        Field field = BoundSql.class.getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, sql);


        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
