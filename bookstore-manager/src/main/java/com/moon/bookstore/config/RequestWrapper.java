package com.moon.bookstore.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 由于 request getInputStream 和 getReader 只能读取一次，
 * 所以需要保存对应的数据
 *
 * @author yujiangtao
 * @date 2021/5/12 下午11:54
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(RequestWrapper.class);

    private byte[] body;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len = request.getInputStream().read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            try {
                if(bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                logger.error("", e);
            }
        }

        this.body = bos.toByteArray();
//        StringBuilder sb = new StringBuilder();
//        String line;
//        BufferedReader reader = null;
//        try {
//            reader = request.getReader();
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//        } catch (IOException e) {
//            logger.error("读取请求失败！", e);
//        }
//        String body = sb.toString();
//        this.body = body.getBytes(StandardCharsets.UTF_8);
    }

    public String getBody() {
        return new String(body, StandardCharsets.UTF_8);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
