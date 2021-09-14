package com.moon.bookstore.api.service;

import java.util.Set;

/**
 * @author yujiangtao
 * @date 2020/11/17 下午3:18
 */
public interface ITaskRelationService {

    Set<String> getAllRelatedTaskIds(String taskId);
}
