package com.moon.bookstore.service.spider;

import com.moon.bookstore.api.entity.Chapter;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * 章节处理器
 *
 * @author yujiangtao
 * @date 2021/1/11 下午11:06
 */
public class ChapterPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("div[@id=list]/dl/dd").nodes();
        if(nodes != null && nodes.size() > 0) {
            for (int i = 0; i < nodes.size(); i++) {
                String href = page.getUrl() + "/" + nodes.get(i).xpath("a/@href").toString();
                Request request = new Request(href);
                request.putExtra("no", i+1);
                page.addTargetRequest(request);
            }
        } else {
            String bookName = page.getHtml().xpath("div[@class=box_con]/div[@class=bookname]/h1/text()").toString();
            String content = page.getHtml().xpath("div[@class=box_con]/div[@id=content]/html()").toString();
            int no = (int)page.getRequest().getExtra("no");
            Chapter chapter = new Chapter();
            chapter.setName(bookName);
            chapter.setContent(content);
            chapter.setNo(no);
            chapter.setBookId(4);
            page.putField("chapter", chapter);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
