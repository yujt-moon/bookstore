package com.moon.bookstore.controller;

import com.moon.bookstore.common.RestResponse;
import com.moon.bookstore.api.vo.DecimalVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author yujiangtao
 * @date 2021/8/30 下午10:09
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/serialize")
    public RestResponse<DecimalVo> serialDecimal() {
        return RestResponse.ok(new DecimalVo(1, new BigDecimal(12.33547), new BigDecimal(4.14255), new BigDecimal(12.3454), null));
    }
}
