package com.moon.bookstore.service.spider;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import java.io.File;
import java.net.URL;

/**
 * 启动 spider
 * @author yujiangtao
 * @date 2020/7/16 下午8:13
 */
public class Main {
    public static void main(String[] args) {
        /*Spider.create(new BookPageProcessor())
                .addUrl("https://www.qidian.com/all")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new BookPipeline())
                .thread(1).start();*/

        /*ResultItems items  = (ResultItems) Spider.create(new TxVideoProcessor())
                .addPipeline(new ConsolePipeline())
                .get("https://v.qq.com/x/cover/xnjrrzldydq0xuo.html");

        List<String> urls = (List<String>)items.get("urls");*/

        Spider.create(new ZHBookPageProcessor())
                .addUrl("http://book.zongheng.com/store/c0/c0/b0/u0/p1/v9/s9/t0/u0/i1/ALL.html")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new BookPipeline())
                .thread(1).start();

        /*Spider.create(new ChapterPageProcessor())
                .addUrl("http://www.biquge.info/34_3746")
                .addPipeline(new ConsolePipeline())
                .thread(1)
                .start();*/

        /*Spider.create(new ImageProcessor())
                .addUrl("https://www.b9093.com/photo/photo_list.html?photo_type=21&page_index=1")
                //.addPipeline(new ConsolePipeline())
                .addPipeline(new ImageDownloadPipeline())
                .thread(1)
                .start();*/
    }

    @Test
    public void writeUrl() throws Exception {
        URL url = new URL("https://pic.hjimg8.com/pic/isz9eu.jpg");
        FileUtils.copyURLToFile(url, new File("/home/yujt/Videos/其他/图片/isz9eu.jpg"));
    }
}
