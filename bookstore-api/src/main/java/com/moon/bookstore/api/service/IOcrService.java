package com.moon.bookstore.api.service;

/**
 * @author yujiangtao
 * @date 2020/10/6 下午4:20
 */
public interface IOcrService {

    String readWordFromImage(byte[] images);
}
