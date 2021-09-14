package com.moon.bookstore.api.service;

import com.moon.bookstore.api.vo.MonthSignInfo;
import com.moon.bookstore.api.vo.SignResult;

import java.util.Date;

/**
 *
 * 签到服务类
 * @author yujiangtao
 * @date 2021/7/19 下午3:50
 */
public interface IUserSignService {

    /**
     * 用户签到
     * @param userId
     * @param date
     * @return
     */
    SignResult signIn(String userId, Date date);

    /**
     * 获取用户当月的签到信息
     * @param userId
     * @return
     */
    MonthSignInfo getUserSignInfo(String userId);

    /**
     * 校验当前用户当前时间是否已经签到
     * @param userId
     * @param date
     * @return
     */
    boolean checkSign(String userId, Date date);
}
