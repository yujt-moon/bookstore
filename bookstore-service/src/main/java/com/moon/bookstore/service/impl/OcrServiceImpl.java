package com.moon.bookstore.service.impl;

import com.baidu.aip.ocr.AipOcr;
import com.google.common.collect.Maps;
import com.moon.bookstore.api.service.IOcrService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author yujiangtao
 * @date 2020/10/6 下午5:35
 */
@Service
@Slf4j
public class OcrServiceImpl implements IOcrService {

    @Value("${baidu.ocr.appId}")
    private String appId;

    @Value("${baidu.ocr.apiKey}")
    private String apiKey;

    @Value("${baidu.ocr.secretKey}")
    private String secretKey;

    @Override
    public String readWordFromImage(byte[] images) {
        // 初始化一个 AipOcr
        AipOcr client = new AipOcr(appId, apiKey, secretKey);

        HashMap<String, String> options = Maps.newHashMap();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        JSONObject jsonObject = client.basicGeneral(images, options);
        StringBuffer result = new StringBuffer();
        if(jsonObject != null) {
            log.debug(jsonObject.toString(2));
            JSONArray wordsResult = jsonObject.getJSONArray("words_result");
            for (Object object : wordsResult) {
                String words = ((JSONObject) object).getString("words") + "\n";
                result.append(words);
            }
        }
        return result.toString();
    }
}
