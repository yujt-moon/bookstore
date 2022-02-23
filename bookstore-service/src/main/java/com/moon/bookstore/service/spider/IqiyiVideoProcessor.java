package com.moon.bookstore.service.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * 爱奇艺
 * @author yujiangtao
 * @date 2020/7/18 下午8:48
 */
public class IqiyiVideoProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("ul[@class='qy-episode-num']").nodes();
        nodes.forEach(node -> {
            node.xpath("li[@class='select-item']/a");
        });
    }

    @Override
    public Site getSite() {
        return null;
    }
}
