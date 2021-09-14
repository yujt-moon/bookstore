package com.moon.bookstore.controller;

import com.alibaba.fastjson.JSON;
import com.moon.bookstore.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2021/1/22 下午9:39
 */
@Slf4j
@RestController
@RequestMapping("/api/excel/")
public class ExcelController {

    @GetMapping("/download")
    public void downloadTemplate(HttpServletResponse response) {
        List<String> columns = Arrays.asList("姓名", "年龄", "性别");
        ExcelUtil.downloadTemplate(response, columns, "学生姓名.xls");
    }

    @PostMapping("/upload")
    public void uploadExcel(HttpServletRequest request, MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            List<List<String>> lists = ExcelUtil.importExcel(inputStream);
            log.info("导入信息：{}", JSON.toJSONString(lists));
        } catch (IOException e) {
            log.error("上传 excel 失败:", e);
        }
    }
}
