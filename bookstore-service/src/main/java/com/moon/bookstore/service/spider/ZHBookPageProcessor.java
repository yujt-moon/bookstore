package com.moon.bookstore.service.spider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moon.bookstore.api.constants.RedisConstants;
import com.moon.bookstore.api.entity.Book;
import com.moon.bookstore.api.entity.BookCategory;
import com.moon.bookstore.service.mapper.BookCategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 纵横页面处理器（http://book.zongheng.com/store/c0/c0/b0/u0/p1/v9/s9/t0/u0/i1/ALL.html）
 * @author yujiangtao
 * @date 2020/7/21 下午7:28
 */
@Slf4j
@Component
public class ZHBookPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private static volatile int pageNo = 1;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private BookCategoryMapper categoryMapper;

    @Override
    public void process(Page page) {
        List<Book> books = new ArrayList<>();
        List<Selectable> nodes = page.getHtml().xpath("div[@class='store_collist']/div[@class='bookbox']").nodes();
        nodes.forEach(node -> {
            String bookCover = node.xpath("div[@class='bookimg']/a/img/@src").toString();
            String bookLink = node.xpath("div[@class='bookinfo']/div[@class='bookname']/a/@href").toString();
            String bookName = node.xpath("div[@class='bookinfo']/div[@class='bookname']/a/text()").toString();
            String author = node.xpath("div[@class='bookinfo']/div[@class='bookilnk']/a[1]/text()").toString();
            String categoryName = node.xpath("div[@class='bookinfo']/div[@class='bookilnk']/a[2]/text()").toString();
            String status = node.xpath("div[@class='bookinfo']/div[@class='bookilnk']/span[1]/text()").toString();
            String intro = node.xpath("div[@class='bookinfo']/div[@class='bookintro']/text()").toString();
            System.out.println("封面：" + bookCover);
            System.out.println("小说链接：" + bookLink);
            System.out.println("小说名：" + bookName);
            System.out.println("作者：" + author);
            System.out.println("分类：" + categoryName);
            System.out.println("状态：" + status);
            System.out.println("简介：" + intro);
            Book book = new Book();
            book.setName(bookName);
            book.setBookCover(bookCover);
            book.setCategoryId(getCategoryId(categoryName));
            book.setStatus(status);
            book.setAuthorId(author);
            book.setIntro(intro);
            books.add(book);
        });

        page.putField("books", books);

        page.addTargetRequest("http://book.zongheng.com/store/c0/c0/b0/u0/p" + (++pageNo) + "/v9/s9/t0/u0/i1/ALL.html");
    }

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 获取类目id
     * @param categoryName
     * @return
     */
    private Integer getCategoryId(String categoryName) {
        if(!redisTemplate.hasKey(RedisConstants.CATEGORY_NAME_ID)) {
            List<BookCategory> bookCategories = categoryMapper.selectList(new QueryWrapper<BookCategory>());
            if(CollectionUtils.isNotEmpty(bookCategories)) {
                for (BookCategory bookCategory : bookCategories) {
                    redisTemplate.opsForHash().put(RedisConstants.CATEGORY_NAME_ID, bookCategory.getCategoryName(), bookCategory.getId() + "");
                }
            }
        }
        Object id = redisTemplate.opsForHash().get(RedisConstants.CATEGORY_NAME_ID, categoryName);
        if(id != null) {
            log.info("categoryName = {} --> categoryId = {}", categoryName, id.toString());
        } else {
            log.warn("categoryName = {} 找不到对应的 categoryId，默认为0", categoryName);
            return 0;
        }
        return Integer.valueOf(id.toString());
    }
}
