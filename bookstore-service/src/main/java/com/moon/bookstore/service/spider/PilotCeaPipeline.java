package com.moon.bookstore.service.spider;

import com.moon.bookstore.api.entity.PilotCea;
import com.moon.bookstore.api.service.IPilotCeaService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2022/2/22 下午5:36
 */
@Component
public class PilotCeaPipeline implements Pipeline {

    @Autowired
    private IPilotCeaService pilotCeaService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<PilotCea> list = (List<PilotCea>) resultItems.get("list");
        if (CollectionUtils.isNotEmpty(list)) {
            pilotCeaService.saveBatch(list);
        }
    }
}
