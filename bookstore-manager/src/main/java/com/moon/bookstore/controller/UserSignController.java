package com.moon.bookstore.controller;

import com.moon.bookstore.common.RestResponse;
import com.moon.bookstore.api.service.IUserSignService;
import com.moon.bookstore.api.vo.MonthSignInfo;
import com.moon.bookstore.api.vo.SignResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 用户签到控制器类
 * @author yujiangtao
 * @date 2021/7/20 下午3:26
 */
@RestController
@RequestMapping("/api/userSign")
public class UserSignController {

    @Autowired
    private IUserSignService userSignService;

    @GetMapping("/signIn")
    @ApiOperation("签到")
    public RestResponse<SignResult> signIn(@RequestParam String userId,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return RestResponse.ok(userSignService.signIn(userId, date));
    }

    @GetMapping("/getSignInfo/{userId}")
    @ApiOperation("查询用户签到信息")
    public RestResponse<MonthSignInfo> getSignInfo(@PathVariable String userId) {
        return RestResponse.ok(userSignService.getUserSignInfo(userId));
    }
}
