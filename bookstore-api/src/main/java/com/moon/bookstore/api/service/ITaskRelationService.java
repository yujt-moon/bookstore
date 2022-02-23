package com.moon.bookstore.api.service;

import java.util.Set;

/**
 * @author yujiangtao
 * @date 2020/11/17 下午3:18
 */
public interface ITaskRelationService {

    /**
     * 获取所有相关任务
     * @param taskId 任务id
     * @return 任务集合
     */
    Set<String> getAllRelatedTaskIds(String taskId);
}
