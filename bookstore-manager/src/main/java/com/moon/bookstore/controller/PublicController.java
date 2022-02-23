package com.moon.bookstore.controller;

import com.moon.bookstore.common.RestResponse;
import com.moon.bookstore.api.service.IPublicService;
import com.moon.bookstore.api.service.ITaskRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 *
 * 公共控制器(存放无法分类的控制器)
 * @author yujiangtao
 * @date 2020/11/17 下午3:22
 */
@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private ITaskRelationService taskRelationService;

    @Autowired
    private IPublicService publicService;

    @PostMapping("/task/{taskId}")
    public Set<String> getAllRelatedTaskIds(@PathVariable String taskId) {
        return taskRelationService.getAllRelatedTaskIds(taskId);
    }

    @PostMapping("/command")
    public RestResponse<String> executeCommand(@RequestBody String command) {
        return RestResponse.ok(publicService.executeCommand(command));
    }
}
