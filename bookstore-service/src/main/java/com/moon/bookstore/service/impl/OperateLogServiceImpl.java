package com.moon.bookstore.service.impl;

import com.moon.bookstore.api.entity.OperateLog;
import com.moon.bookstore.api.service.IOperateLogService;
import com.moon.bookstore.service.mapper.OperateLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yujiangtao
 * @date 2021/9/14 下午5:47
 */
@Service
public class OperateLogServiceImpl implements IOperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public void addOperateLog(OperateLog operateLog) {
        operateLogMapper.insert(operateLog);
    }
}
