package com.moon.bookstore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.moon.bookstore.api.service.IPublicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author yujiangtao
 * @date 2021/7/9 上午10:14
 */
@Service
@Slf4j
public class PublicServiceImpl implements IPublicService {
    @Override
    public String executeCommand(String command) {
        command = JSONObject.parseObject(command).getString("command");
        if(StringUtils.isBlank(command)) {
            return null;
        }
        BufferedReader br = null;
        String[] commands = new String[]{"/bin/bash", "-c", command};
        StringBuffer result = new StringBuffer();
        try {
            Process process = Runtime.getRuntime().exec(commands);
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while((line = br.readLine()) != null) {
                result.append(line + "\n");
            }
            if(result.length() > 0) {
                result.delete(result.length() - 1, result.length());
            }
        } catch (Exception e) {
            log.error("执行命令出错: {}", e.getMessage(), e);
        }
        return result.toString();
    }

    @Override
    public void preview(InputStream is, String fileType, HttpServletResponse response) {
//        if ("xlsx".equals(fileType)) {
//            fileType = "xls";
//        }
//        if ("docx".equalsIgnoreCase(fileType)) {
//            fileType = "doc";
//        }
//        try {
//            InputStream in = FileConvertUtil.convertCommonByStream(is, fileType);
//            OutputStream outputStream = response.getOutputStream();
//            //创建存放文件内容的数组
//            byte[] buff = new byte[1024];
//            //所读取的内容使用n来接收
//            int n;
//            //当没有读取完时,继续读取,循环
//            while ((n = in.read(buff)) != -1) {
//                //将字节数组的数据全部写入到输出流中
//                outputStream.write(buff, 0, n);
//            }
//            //强制将缓存区的数据进行输出
//            outputStream.flush();
//            //关流
//            outputStream.close();
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String fileUrl = "/home/yujt/Desktop/jls8.pdf";
        try {
            InputStream in = new FileInputStream(new File(fileUrl));
            ServletOutputStream outputStream = response.getOutputStream();
            //创建存放文件内容的数组
            byte[] buff = new byte[1024];
            //所读取的内容使用n来接收
            int n;
            //当没有读取完时,继续读取,循环
            while ((n = in.read(buff)) != -1) {
                //将字节数组的数据全部写入到输出流中
                outputStream.write(buff, 0, n);
            }
            //强制将缓存区的数据进行输出
            outputStream.flush();
            //关流
            outputStream.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
