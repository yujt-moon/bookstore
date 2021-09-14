package com.moon.bookstore.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moon.bookstore.api.entity.TaskRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author yujiangtao
 * @date 2020/11/17 下午3:04
 */
@Mapper
public interface TaskRelationMapper extends BaseMapper<TaskRelation> {

    Set<String> selectDirectRel(@Param("taskId") String taskId);
}
