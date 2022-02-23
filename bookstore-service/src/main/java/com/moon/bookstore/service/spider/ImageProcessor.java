package com.moon.bookstore.service.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/9/20 上午1:53
 */
public class ImageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private final String baseUrl = "https://www.b9093.com";

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//div[@class=movie_list]/ul/li").nodes();
        if(nodes != null && nodes.size() > 0) {
            nodes.forEach(node -> {
                String href = baseUrl + node.xpath("a/@href").toString();
                System.out.println(href);
                page.addTargetRequest(href);
            });
        } else {
            String title = page.getHtml().xpath("div[@class=post_title]/h1/text()").toString();
            System.out.println(title);
            List<Selectable> node2s = page.getHtml().xpath("div[@class=post_content]/a").nodes();
            List<String> urls = new ArrayList<>();
            node2s.forEach(node -> {
                String imageUrl = node.xpath("img/@src").toString();
                System.out.println(imageUrl);
                urls.add(imageUrl);
            });
            page.putField("title", title);
            page.putField("urls", urls);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
