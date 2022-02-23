package com.moon.bookstore.service.spider;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/9/20 上午1:56
 */
public class ImageDownloadPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        List<String> urls = resultItems.get("urls");
        if(StringUtils.isBlank(title)) {
            return;
        }

        File file = new File("/media/yujt/_dde_data1/home/图片/日韩激情/" + title);
        if(!file.exists()) {
            file.mkdir();
        }

        try {
            for (int i = 0; i < urls.size(); i++) {
                URL url1 = new URL(urls.get(i));
                FileUtils.copyURLToFile(url1, new File("/media/yujt/_dde_data1/home/图片/日韩激情/" + title + "/" + i + ".jpg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
