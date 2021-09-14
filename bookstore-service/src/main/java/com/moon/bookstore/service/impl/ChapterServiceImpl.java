package com.moon.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moon.bookstore.api.entity.Chapter;
import com.moon.bookstore.api.service.IChapterService;
import com.moon.bookstore.service.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2021/1/12 上午12:14
 */
@Service
public class ChapterServiceImpl implements IChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public boolean addChapter(Chapter chapter) {
        return chapterMapper.insert(chapter) > 0;
    }

    @Override
    public List<Chapter> getChapters(Integer bookId) {
        return chapterMapper.selectList(new QueryWrapper<Chapter>()
                .eq("book_id", bookId).orderByAsc("no"));
    }

    @Override
    public Chapter getChapter(Integer id) {
        return chapterMapper.selectById(id);
    }
}
