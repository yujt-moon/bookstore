package com.moon.bookstore.service.spider;

import com.moon.bookstore.api.entity.Ccer;
import com.moon.bookstore.api.service.ICcerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2021/12/29 下午5:10
 */
@Component
public class CcerPipeline implements Pipeline {

    @Autowired
    private ICcerService ccerService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Ccer> ccerList = (List<Ccer>)resultItems.get("ccerList");
        ccerService.saveBatch(ccerList);
    }
}
