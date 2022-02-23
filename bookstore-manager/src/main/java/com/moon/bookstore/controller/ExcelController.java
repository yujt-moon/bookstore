package com.moon.bookstore.controller;

import com.alibaba.fastjson.JSON;
import com.moon.bookstore.api.service.IPublicService;
import com.moon.bookstore.common.util.ExcelUtil;
import com.moon.bookstore.common.util.FileUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private IPublicService publicService;

    @GetMapping("/download")
    public void downloadTemplate(HttpServletResponse response) {
        List<String> columns = Arrays.asList("姓名", "年龄", "性别");
        ExcelUtil.downloadTemplate(response, columns, "学生姓名.xls");
    }

    @PostMapping("/upload")
    public void uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            List<List<String>> lists = ExcelUtil.importExcel(inputStream);
            log.info("导入信息：{}", JSON.toJSONString(lists));
        } catch (IOException e) {
            log.error("上传 excel 失败:", e);
        }
    }

    @ApiOperation("文件预览")
    @PostMapping("/preview")
    public void preview(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        try {
            InputStream inputStream = file.getInputStream();
            String fileSuffix = FileUtil.getFileSuffix(file.getOriginalFilename());
            publicService.preview(inputStream, fileSuffix, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
