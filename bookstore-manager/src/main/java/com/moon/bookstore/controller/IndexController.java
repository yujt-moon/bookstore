package com.moon.bookstore.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yujiangtao
 * @date 2020/7/14 下午8:46
 */
@RestController
@RequestMapping("/api")
public class IndexController extends BaseController {

    @PostMapping("/index")
    public String index() {
        return "This is a springboot project.";
    }

}
