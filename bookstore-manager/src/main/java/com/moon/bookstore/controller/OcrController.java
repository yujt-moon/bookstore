package com.moon.bookstore.controller;

import com.moon.bookstore.common.RestResponse;
import com.moon.bookstore.api.service.IOcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author yujiangtao
 * @date 2020/10/6 下午6:34
 */
@RestController
@RequestMapping("/api/ocr")
public class OcrController {

    @Autowired
    private IOcrService ocrService;

    @PostMapping("/image")
    public RestResponse<String> readImageWord(@RequestBody MultipartFile file) {

        String result = "";
        try {
            result = ocrService.readWordFromImage(file.getBytes());
        } catch (IOException e) {
            RestResponse.fail();
        }
        return RestResponse.ok(result);
    }
}
