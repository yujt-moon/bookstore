package com.moon.bookstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yujiangtao
 * @date 2020/11/17 下午3:02
 */
@TableName("task_relation")
@Data
public class TaskRelation {

    private int id;

    private int taskId;

    private int relatedTaskId;
}
