package com.moon.bookstore.api.service;

import com.moon.bookstore.api.entity.OperateLog;

/**
 * 操作日志 service
 * @author yujiangtao
 * @date 2021/9/14 下午5:41
 */
public interface IOperateLogService {

    /**
     * 新增操作日志
     * @param operateLog 操作日志
     */
    void addOperateLog(OperateLog operateLog);
}
