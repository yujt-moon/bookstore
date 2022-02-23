package com.moon.bookstore.service.spider;

import org.apache.commons.collections.CollectionUtils;
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
public class YellowImageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private static volatile int pageNo = 1;

    private final String urlPrefix = "https://www.tang103.top/";

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//table[@id=threadlisttableid]//th[@class=common or @class=new]").nodes();
        if(nodes != null && nodes.size() > 0) {
            nodes.forEach(node -> {
                List<Selectable> hrefs = node.xpath("a").nodes();
                for (Selectable anode : hrefs) {
                    String href = anode.xpath("a/@href").toString();
                    if(!href.contains("javascript")) {
                        System.out.println("href: " + href);
                        page.addTargetRequest(href);
                    }
                }
            });
            if(pageNo >= 323) {
                return;
            }
            System.out.println("current page: " + pageNo);
            page.addTargetRequest(String.format(getNextPageUrl(page.getUrl().toString()), ++pageNo));
        } else {
            String title = page.getHtml().xpath("span[@id=thread_subject]/text()").toString();
            System.out.println(title);
            List<Selectable> node2s = page.getHtml().xpath("div[@id=postlist]//div[@class=pattl]//p[@class=mbn]//a").nodes();
            List<String> urls = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(node2s)) {
                node2s.forEach(node -> {
                    String imageUrl = urlPrefix + node.xpath("a/@href").toString();
                    System.out.println(imageUrl);
                    urls.add(imageUrl);
                });
            } else {
                node2s = page.getHtml().xpath("div[@id=postlist]//td[@class=t_f]//img").nodes();
                for (Selectable node2 : node2s) {
                    String imageUrl = node2.xpath("img/@file").toString();
                    System.out.println(imageUrl);
                    urls.add(imageUrl);
                }
            }
            page.putField("title", title);
            page.putField("urls", urls);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }


    private String getNextPageUrl(String url) {
        int index_ = url.lastIndexOf("-");
        int indexDot = url.lastIndexOf(".");
        return url.substring(0, index_ + 1) + "%d" + ".html";
    }
}
