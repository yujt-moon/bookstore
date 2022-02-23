package com.moon.bookstore.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moon.bookstore.api.entity.User;
import com.moon.bookstore.api.request.UserAddRequest;
import com.moon.bookstore.api.request.UserModifyRequest;
import com.moon.bookstore.api.request.UserPageRequest;
import com.moon.bookstore.api.service.IUserService;
import com.moon.bookstore.common.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yujiangtao
 * @date 2021/10/26 下午2:17
 */
@RequestMapping("/api/user")
@RestController
@SuppressWarnings("unused")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping("/page")
    public RestResponse<IPage<User>> getUserPage(@RequestBody UserPageRequest request) {
        return RestResponse.ok(userService.getPage(request));
    }

    @PostMapping("/add")
    public RestResponse<Long> addUser(@RequestBody UserAddRequest request) {
        return RestResponse.ok(userService.addUser(request));
    }

    @GetMapping("/del/{id}")
    public RestResponse<Boolean> delUser(@PathVariable Long id) {
        return RestResponse.ok(userService.delUser(id));
    }

    @PostMapping("/modify")
    public RestResponse<Boolean> modifyUser(@RequestBody UserModifyRequest request) {
        return RestResponse.ok(userService.modifyUser(request));
    }
}
