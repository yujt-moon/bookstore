package com.moon.bookstore.api.service;

/**
 * @author yujiangtao
 * @date 2020/10/6 下午4:20
 */
public interface IOcrService {

    /**
     * 文字识别
     * @param images 图片二进制信息
     * @return 文字
     */
    String readWordFromImage(byte[] images);
}
