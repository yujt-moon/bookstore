package com.moon.bookstore.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.moon.bookstore.api.constants.ESConstants;
import com.moon.bookstore.api.constants.RedisConstants;
import com.moon.bookstore.api.entity.Book;
import com.moon.bookstore.api.entity.BookCategory;
import com.moon.bookstore.api.request.BookAddRequest;
import com.moon.bookstore.api.request.BookPageRequest;
import com.moon.bookstore.api.service.IBookService;
import com.moon.bookstore.common.exception.BusiException;
import com.moon.bookstore.service.mapper.BookCategoryMapper;
import com.moon.bookstore.service.mapper.BookMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yujiangtao
 * @date 2020/7/17 下午2:18
 */
@Service
@Slf4j
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
        implements IBookService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private DataSource dataSource;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    @Override
    public boolean batchAdd(List<Book> books) {
        books.stream().forEach(book -> baseMapper.insert(book));
        return true;
    }

    @Override
    public IPage<Book> page(BookPageRequest request) {
        Page<Book> page = new Page<>(request.getPageNo(), request.getPageSize());
        IPage<Book> books = baseMapper.selectPage(page, new QueryWrapper<>());
        List<Book> records = books.getRecords();
        /*if(CollectionUtils.isNotEmpty(records)) {
            // 查询分类名称
            if(!redisTemplate.hasKey(RedisConstants.CATEGORY_ID_NAME)) {
                List<BookCategory> bookCategories = bookCategoryMapper.selectList(new QueryWrapper<BookCategory>());
                if(CollectionUtils.isNotEmpty(bookCategories)) {
                    bookCategories.forEach(x -> {
                        redisTemplate.opsForHash().put(RedisConstants.CATEGORY_ID_NAME, x.getId() + "", x.getCategoryName());
                    });
                }
            }

            records.forEach(record -> {
                record.setCategoryName(redisTemplate.opsForHash().get(RedisConstants.CATEGORY_ID_NAME,
                        record.getCategoryId() + "").toString());
            });
        }*/
        return books;
    }

    @Override
    public boolean insertListIntoES() throws Exception {
        // 判断当前索引是否存在
        GetIndexRequest getIndexRequest = new GetIndexRequest(ESConstants.INDEX_BOOK);
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        if(!exists) {
            CreateIndexRequest request = new CreateIndexRequest(ESConstants.INDEX_BOOK);
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.startObject("properties");
                {
                    builder.startObject("name");
                    {
                        builder.field("type", "text");
                        builder.field("analyzer", "ik_max_word");
                    }
                    builder.endObject();
                    builder.startObject("authorId");
                    {
                        builder.field("type", "text");
                        builder.field("analyzer", "ik_max_word");
                    }
                    builder.endObject();
                    builder.startObject("intro");
                    {
                        builder.field("type", "text");
                        builder.field("analyzer", "ik_max_word");
                    }
                    builder.endObject();
                }
                builder.endObject();
            }
            builder.endObject();
            request.mapping(builder);

            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            boolean acknowledged = createIndexResponse.isAcknowledged();
            // 索引创建成功
            if(acknowledged) {
                log.info("索引[{}]创建成功", ESConstants.INDEX_BOOK);
            } else {
                log.error("索引[{}]创建失败", ESConstants.INDEX_BOOK);
                throw new RuntimeException("索引[" + ESConstants.INDEX_BOOK + "]创建失败！");
            }
        }

        List<Book> books = this.baseMapper.selectList(new QueryWrapper<Book>());
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        for (Book book : books) {
            bulkRequest.add(new IndexRequest(ESConstants.INDEX_BOOK)
                    .id(String.valueOf(book.getId()))
                    .source(JSON.toJSONString(book), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }

    @Override
    public IPage<Book> search(BookPageRequest request) {
        IPage<Book> page = new Page<>(request.getPageNo(), request.getPageSize());

        SearchRequest searchRequest = new SearchRequest(ESConstants.INDEX_BOOK);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.multiMatchQuery(request.getKeyword(),
                "name", "authorId", "intro")
                .analyzer("ik_max_word"));
        sourceBuilder.from((request.getPageNo() - 1) * request.getPageSize());
        sourceBuilder.size(request.getPageSize());
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.sort("_score", SortOrder.DESC);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name").field("authorId").field("intro")
                .preTags("<span style='color:red'>")
                .postTags("</span>").
                requireFieldMatch(false);
        sourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(sourceBuilder);

        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            if(hits != null) {
                page.setTotal(hits.getTotalHits().value);
                log.info("查询[{}]命中{}条!", request.getKeyword(), hits.getTotalHits().value);
                SearchHit[] searchHits = hits.getHits();
                List<Book> records = Lists.newArrayList();
                for (SearchHit searchHit : searchHits) {
                    HighlightField intro = searchHit.getHighlightFields().get("intro");
                    HighlightField name = searchHit.getHighlightFields().get("name");
                    HighlightField authorId = searchHit.getHighlightFields().get("authorId");
                    Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                    if(name != null) {
                        StringBuffer highlightIntro = new StringBuffer();
                        for (Text fragment : name.getFragments()) {
                            highlightIntro.append(fragment);
                        }
                        sourceAsMap.put("name", highlightIntro.toString());
                    }
                    if(authorId != null) {
                        StringBuffer highlightIntro = new StringBuffer();
                        for (Text fragment : authorId.getFragments()) {
                            highlightIntro.append(fragment);
                        }
                        sourceAsMap.put("authorId", highlightIntro.toString());
                    }
                    if(intro != null) {
                        StringBuffer highlightIntro = new StringBuffer();
                        for (Text fragment : intro.getFragments()) {
                            highlightIntro.append(fragment);
                        }
                        sourceAsMap.put("intro", highlightIntro.toString());
                    }
                    Book book = BeanUtils.mapToBean(sourceAsMap, Book.class);
                    records.add(book);
                }
                page.setRecords(records);
            }
        } catch (IOException e) {
            log.error("查询失败:{}", e.getMessage(), e);
            throw new BusiException("书籍全文检索失败！");
        }
        return page;
    }

    @Override
    public Book getBookInfo(Long bookId, Long categoryId) {
        return baseMapper.selectOne(new QueryWrapper<Book>().eq("id", bookId)
                .eq("category_id", categoryId));
    }

    @Override
    public List<Book> getShardingBooks() {
        return null;
    }

    @Override
    public Boolean addBook(BookAddRequest request) {
        Book book = new Book();
        org.springframework.beans.BeanUtils.copyProperties(request, book);
        return this.baseMapper.insert(book) > 0;
    }
}
