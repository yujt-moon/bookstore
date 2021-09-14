package com.moon.bookstore.service.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/7/18 下午4:52
 */
public class TxVideoProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        List<String> urls = new ArrayList<>();
        List<Selectable> nodes = page.getHtml().xpath("div[@class='mod_episode']/span").nodes();
        nodes.stream().forEach(node -> {
            String videoUrl = node.xpath("a/@href").toString();
            urls.add("https://v.qq.com" + videoUrl);
        });

        for (int i = 0; i < urls.size(); i++) {
            System.out.printf("%d : %s\n", i+1, urls.get(i));
        }

        page.putField("urls", urls);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
