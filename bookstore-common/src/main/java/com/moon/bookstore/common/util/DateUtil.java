package com.moon.bookstore.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 日期工具类
 * @author yujiangtao
 * @date 2021/7/20 下午2:32
 */
public class DateUtil {

    /**
     * 将 LocalDate 转换成 Date
     * @param localDate LocalDate
     * @return Date
     */
    @SuppressWarnings("unused")
    public static Date localDate2Date(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zoneId).toInstant();
        return Date.from(instant);
    }

    /**
     * 将 Date 转换成 LocalDate
     * @param date Date
     * @return LocalDate
     */
    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return localDateTime.toLocalDate();
    }
}
