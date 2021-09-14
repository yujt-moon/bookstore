package com.moon.bookstore.common.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * excel 工具类
 *
 * @author yujiangtao
 * @date 2021/1/22 下午5:24
 */
public class ExcelUtil {

    /**
     * 下载空白模板
     * @param response 响应
     * @param columnNames 列名
     * @param fileName 文件名
     */
    public static void downloadTemplate(HttpServletResponse response, List<String> columnNames, String fileName) {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/octet-stream");
        // 下载文件的默认名称
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SXSSFWorkbook workbook = new SXSSFWorkbook();// 声明一个工作薄
        SXSSFSheet sheet = workbook.createSheet(); // 生成一个表格
        SXSSFRow row = sheet.createRow(0);// 产生表格标题行
        for (int i = 0; i < columnNames.size(); i++) {
            row.createCell(i).setCellValue(new XSSFRichTextString(columnNames.get(i)));
        }

        try {
            workbook.write(response.getOutputStream());
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 导入 excel
     * @param is InputStream
     * @return List<List<String>>
     */
    public static List<List<String>> importExcel(InputStream is) {
        try {
            //HSSFWorkbook workbook = new HSSFWorkbook(is);
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            List<List<String>> data = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                List<String> obj = new ArrayList<>();
                Row row = sheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    obj.add(row.getCell(j).getStringCellValue());
                }
                data.add(obj);
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
