package com.moon.bookstore.service.impl;

import com.moon.bookstore.api.service.ITaskRelationService;
import com.moon.bookstore.service.mapper.TaskRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author yujiangtao
 * @date 2020/11/17 下午3:20
 */
@Service
@Transactional
public class TaskRelationServiceImpl implements ITaskRelationService {

    @Autowired
    private TaskRelationMapper taskRelationMapper;

    @Override
    public Set<String> getAllRelatedTaskIds(String taskId) {
        Set<String> already = new LinkedHashSet<>();
        Set<String> directRel = taskRelationMapper.selectDirectRel(taskId);
        already.addAll(directRel);
        directRel.stream().forEach(x -> recusiveGet(x, already));
        return already;
    }

    private void recusiveGet(String taskId, Set<String> already) {
        Set<String> temps = taskRelationMapper.selectDirectRel(taskId);
        if(temps == null || temps.size() == 0) {
            return;
        }
        for (String temp : temps) {
            if(already.contains(temp)) {
                continue;
            } else {
                already.add(temp);
                recusiveGet(temp, already);
            }
        }
    }
}
