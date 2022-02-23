package com.moon.bookstore.service.spider;

import com.moon.bookstore.api.entity.Book;
import com.moon.bookstore.common.util.UnicodeConvertUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

/**
 *
 * 起点页面处理器
 * @author yujiangtao
 * @date 2020/7/16 下午2:42
 */
public class QDBookPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private Map<String, String> unicodeNumMap = new HashMap<>();

    private void initMap() {
        unicodeNumMap.put("\\ud822\\udcc9", "0");
        unicodeNumMap.put("\\ud822\\udcbe", "1");
        unicodeNumMap.put("\\ud822\\udcc7", "2");
        unicodeNumMap.put("\\ud822\\udcc1", "3");
        unicodeNumMap.put("\\ud822\\udcc5", "4");
        unicodeNumMap.put("\\ud822\\udcc0", "5");
        unicodeNumMap.put("\\ud822\\udcc3", "6");
        unicodeNumMap.put("\\ud822\\udcc8", "7");
        unicodeNumMap.put("\\ud822\\udcc2", "8");
        unicodeNumMap.put("\\ud822\\udcc4", "9");
        unicodeNumMap.put("\\ud822\\udcc6", ".");
    }

    private volatile static int pageNum = 1;

    private String convertToNum(String unicodeString) {
        String converted = UnicodeConvertUtil.convert(unicodeString);
        Iterator<Map.Entry<String, String>> iterator = unicodeNumMap.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            converted = converted.replaceAll(next.getKey(), next.getValue());
        }
        return converted;
    }

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("ul[@class='all-img-list cf']/li").nodes();
        List<Book> books = new ArrayList<>();
        for (Selectable node : nodes) {
            Book book = new Book();
            String novel = node.xpath("h4/a/text()").toString();
            String author = node.xpath("p[@class='author']/a[@class='name']/text()").toString();
            String bookCover = node.xpath("div[@class='book-img-box']/a/img/@src").toString().replaceFirst("//", "https://");
            String categoryName = node.xpath("p[@class='author']/a[2]/text()").toString();
            String subType = node.xpath("p[@class='author']/a[@class='go-sub-type']/text()").toString();
            String status = node.xpath("p[@class='author']/span[1]/text()").toString();
            String intro = node.xpath("p[@class='intro']/text()").toString();
            String count = node.xpath("p[@class='update']/span/span/text()").toString();
            book.setName(novel);
            book.setAuthorId(author);
            book.setBookCover(bookCover);
            // TODO
            //book.setCategoryId(categoryName);
            book.setSubCategoryId(subType);
            book.setStatus(status);
            book.setIntro(intro);
            books.add(book);
        }
        page.putField("books", books);

        page.addTargetRequest("https://www.qidian.com/all?page=" + pageNum++);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
