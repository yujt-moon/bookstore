package com.moon.bookstore.controller;

import com.moon.bookstore.common.RestResponse;
import com.moon.bookstore.api.request.VideoRequest;
import com.moon.bookstore.service.spider.TxVideoProcessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/7/18 下午5:28
 */
@RestController
@RequestMapping("/api/video")
public class VideoController extends BaseController {

    @PostMapping("/showParts")
    public RestResponse<List<String>> getVideoParts(@RequestBody VideoRequest request) {
        PageProcessor processor = null;
        if(request.getUrl().indexOf("v.qq.com/") != -1) {
            processor = new TxVideoProcessor();
        } else if(request.getUrl().indexOf("www.iqiyi.com/") != -1) {

        }
        ResultItems items  = (ResultItems) Spider.create(processor)
                .addPipeline(new ConsolePipeline())
                .get(request.getUrl());
        return RestResponse.ok(items.get("urls"));
    }
}
