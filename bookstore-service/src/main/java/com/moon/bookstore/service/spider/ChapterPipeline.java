package com.moon.bookstore.service.spider;

import com.moon.bookstore.api.entity.Chapter;
import com.moon.bookstore.api.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author yujiangtao
 * @date 2021/1/12 上午12:08
 */
@Component
public class ChapterPipeline implements Pipeline {

    @Autowired
    private IChapterService chapterService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Chapter chapter = (Chapter)resultItems.get("chapter");
        if(chapter != null) {
            chapterService.addChapter(chapter);
        }
    }
}
