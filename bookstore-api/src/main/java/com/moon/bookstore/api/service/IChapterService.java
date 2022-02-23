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
     * @param chapter 章节信息
     * @return 新增结果
     */
    boolean addChapter(Chapter chapter);

    /**
     * 获取章节列表
     * @param bookId 书籍id
     * @return 章节列表
     */
    List<Chapter> getChapters(Long bookId);

    /**
     * 查询章节信息
     * @param id 章节id
     * @return 章节信息
     */
    Chapter getChapter(Long id);
}
