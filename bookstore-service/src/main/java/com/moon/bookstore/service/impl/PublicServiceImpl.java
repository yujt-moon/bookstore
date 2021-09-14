package com.moon.bookstore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.moon.bookstore.api.service.IPublicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
}
