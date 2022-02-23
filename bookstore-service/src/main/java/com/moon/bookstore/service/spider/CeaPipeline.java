package com.moon.bookstore.service.spider;

import com.moon.bookstore.api.entity.Cea;
import com.moon.bookstore.api.service.ICeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author yujiangtao
 * @date 2021/12/29 下午5:10
 */
@Component
public class CeaPipeline implements Pipeline {

    @Autowired
    private ICeaService ceaService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Cea cea = (Cea) resultItems.get("cea");
        if (cea != null) {
            ceaService.save(cea);
        }
    }
}
