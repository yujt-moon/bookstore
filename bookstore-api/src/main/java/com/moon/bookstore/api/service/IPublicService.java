package com.moon.bookstore.api.service;

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
}
