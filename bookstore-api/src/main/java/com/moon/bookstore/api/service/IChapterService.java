package com.moon.bookstore.api.service;

import com.moon.bookstore.api.entity.Chapter;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2021/1/12 上午12:13
 */
public interface IChapterService {

    /**
     * 新增章节信息
     * @param chapter
     * @return
     */
    boolean addChapter(Chapter chapter);

    /**
     * 获取章节列表
     * @param bookId
     * @return
     */
    List<Chapter> getChapters(Integer bookId);

    /**
     * 查询章节信息
     * @param id
     * @return
     */
    Chapter getChapter(Integer id);
}
