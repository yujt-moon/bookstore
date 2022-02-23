package com.moon.bookstore.controller;

import com.moon.bookstore.common.RestResponse;
import com.moon.bookstore.service.spider.*;
import com.moon.bookstore.service.spider.fixssl.HttpClientDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 爬虫控制器
 * @author yujiangtao
 * @date 2020/7/17 下午1:36
 */
@RestController
@RequestMapping("/api/spider")
public class SpiderController {

    @Autowired
    @Qualifier("bookPipeline")
    private Pipeline pipeline;

    @Autowired
    @Qualifier("chapterPipeline")
    private Pipeline chapterPipeline;

    @Autowired
    @Qualifier("ceaPipeline")
    private Pipeline ceaPipeline;

    @Autowired
    @Qualifier("ccerPipeline")
    private Pipeline ccerPipeline;

    @Autowired
    private ZHBookPageProcessor zhBookPageProcessor;

    @GetMapping("/book/qd")
    public RestResponse bookSpider() {
        Spider.create(new QDBookPageProcessor())
                .addUrl("https://www.qidian.com/all")
                .addPipeline(new ConsolePipeline())
                .addPipeline(pipeline)
                .thread(1).start();
        return RestResponse.ok();
    }

    @GetMapping("/book/zh")
    public RestResponse zhBookSpider() {
        Spider.create(zhBookPageProcessor)
                .addUrl("http://book.zongheng.com/store/c0/c0/b0/u0/p1/v9/s9/t0/u0/i1/ALL.html")
                .addPipeline(new ConsolePipeline())
                .addPipeline(pipeline)
                .thread(5).start();
        return RestResponse.ok();
    }

    @GetMapping("/book/chapter")
    public RestResponse chapterSpider() {
        Spider.create(new ChapterPageProcessor())
                .addUrl("http://www.biquge.info/34_3746")
                .addPipeline(chapterPipeline)
                .thread(5).start();
        return RestResponse.ok();
    }

    @GetMapping("/pic")
    public RestResponse picSpider() {
        Spider.create(new YellowImageProcessor())
                .setDownloader(new HttpClientDownloader())
                .addUrl("https://www.tang103.top/forum-8-1.html")
                .addPipeline(new ImageDownloadPipeline())
                .addPipeline(new ConsolePipeline())
                .thread(5).start();
        return RestResponse.ok();
    }

    @GetMapping("/cea")
    public RestResponse ceaSpider() {
        Spider.create(new CeaProcessor())
                //.addUrl("https://www.cneeex.com/cneeex/catalog/15317/pc/index_6.shtml")
                //.addUrl("https://www.cneeex.com/cneeex/catalog/15317/pc/index_7.shtml")
                .addUrl("https://www.cneeex.com/cneeex/catalog/15317/pc/index_8.shtml")
                .addPipeline(new ConsolePipeline())
                .addPipeline(ceaPipeline)
                .thread(1)
                .start();
        return RestResponse.ok();
    }

    @GetMapping("/ccer")
    public RestResponse ccerSpider() {
        Spider.create(new CcerProcessor())
                .addUrl("https://www.bjets.com.cn/article/jyxx/?")
                .addPipeline(new ConsolePipeline())
                .addPipeline(ccerPipeline)
                .thread(1)
                .start();
        return RestResponse.ok();
    }
}
