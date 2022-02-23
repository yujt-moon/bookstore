package com.moon.bookstore.controller;

import com.moon.bookstore.common.RestResponse;
import com.moon.bookstore.api.entity.Chapter;
import com.moon.bookstore.api.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2021/1/12 下午5:02
 */
@RestController
@RequestMapping("/api/chapter")
public class ChapterController {

    @Autowired
    private IChapterService chapterService;

    @GetMapping("/list/{bookId}")
    public RestResponse<List<Chapter>> getChapters(@PathVariable Long bookId) {
        return RestResponse.ok(chapterService.getChapters(bookId));
    }

    @GetMapping("/detail/{id}")
    public RestResponse<Chapter> getChapter(@PathVariable Long id) {
        return RestResponse.ok(chapterService.getChapter(id));
    }
}
