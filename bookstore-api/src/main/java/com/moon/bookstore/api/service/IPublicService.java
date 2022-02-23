package com.moon.bookstore.api.service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @author yujiangtao
 * @date 2021/7/9 上午10:13
 */
public interface IPublicService {

    /**
     * 执行命令行
     * @param command 命令
     * @return 命令执行结果
     */
    String executeCommand(String command);

    /**
     * 文件预览
     * @param is 文件流
     * @param fileType 文件类型
     * @param response 响应
     */
    void preview(InputStream is, String fileType, HttpServletResponse response);
}
