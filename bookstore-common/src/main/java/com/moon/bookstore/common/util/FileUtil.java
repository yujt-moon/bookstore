package com.moon.bookstore.common.util;

import org.springframework.util.StringUtils;

/**
 * @author yujiangtao
 * @date 2021/11/3 上午10:57
 */
public class FileUtil {

    /**
     * 获取文件名称
     * @param filePath 文件路径
     * @return 文件名称
     */
    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        if (filePath.contains("/")) {
            int slashIndex = filePath.lastIndexOf("/");
            filePath = filePath.substring(slashIndex + 1);
        }
        if (filePath.contains(".")) {
            int dotIndex = filePath.lastIndexOf(".");
            filePath = filePath.substring(0, dotIndex);
        }
        return filePath;
    }

    /**
     * 获取文件后缀
     * @param filePath 文件路径
     * @return 文件后缀
     */
    public static String getFileSuffix(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        if (filePath.contains(".")) {
            int dotIndex = filePath.lastIndexOf(".");
            return filePath.substring(dotIndex + 1);
        }
        return null;
    }
}
