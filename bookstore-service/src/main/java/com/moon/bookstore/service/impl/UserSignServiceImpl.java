package com.moon.bookstore.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moon.bookstore.common.exception.BusiException;
import com.moon.bookstore.api.entity.UserSign;
import com.moon.bookstore.api.service.IUserSignService;
import com.moon.bookstore.common.util.DateUtil;
import com.moon.bookstore.api.vo.MonthSignInfo;
import com.moon.bookstore.api.vo.SignResult;
import com.moon.bookstore.service.mapper.UserSignMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2021/7/19 下午3:57
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserSignServiceImpl extends ServiceImpl<UserSignMapper, UserSign> implements IUserSignService {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");

    /**
     * 每次签到获得的守护星
     */
    private int signStar = 10;

    @Override
    public SignResult signIn(String userId, Date date) {
        LocalDate localDate = DateUtil.date2LocalDate(date);
        String signMonth = localDate.format(formatter);
        // 查询本月是否有签到记录
        UserSign userSign = this.baseMapper.selectOne(new QueryWrapper<UserSign>().lambda()
                .eq(UserSign::getUserKey, userId).eq(UserSign::getSignMonth, signMonth));
        // 创建一条签到记录
        if(userSign == null) {
            userSign = new UserSign();
            userSign.setLastSignDate(date);
            userSign.setSignCount(1);
            userSign.setSignRecord(1 << localDate.getDayOfMonth());
            userSign.setUserKey(userId);
            userSign.setSignMonth(signMonth);
            // 新增签到记录
            this.baseMapper.insert(userSign);
            return SignResult.builder().signCount(1).starCount(signStar).build();
        } else {
            // 当前已存在签到记录
            LocalDate lastSignDate = DateUtil.date2LocalDate(userSign.getLastSignDate());
            // 判断当前日期是否已经进行签到了
            Integer signRecord = userSign.getSignRecord();
            // 当前已经签到过了
            if((signRecord & (1 << localDate.getDayOfMonth())) > 0) {
                throw new BusiException("当前用户当前日期已进行过签到！");
            }
            // 前一天签到了，保持连续签到
            if(localDate.getDayOfMonth() - lastSignDate.getDayOfMonth() == 1) {
                userSign.setSignCount(userSign.getSignCount() + 1);
                // 如果连续十天签到，守护星为 100

            } else {
                // 从头开始计算
                userSign.setSignCount(1);
            }
            userSign.setSignRecord(signRecord | (1 << localDate.getDayOfMonth()));
            userSign.setLastSignDate(date);
            log.info("修改签到信息：{}", JSON.toJSONString(userSign));
            this.baseMapper.updateById(userSign);
            return SignResult.builder().signCount(userSign.getSignCount()).build();
        }
    }

    @Override
    public MonthSignInfo getUserSignInfo(String userId) {
        LocalDate now = LocalDate.now();
        String signMonth = now.format(formatter);
        // 查询当前月份当前用户的签到信息
        UserSign userSign = this.baseMapper.selectOne(new QueryWrapper<UserSign>().lambda()
                .eq(UserSign::getUserKey, userId).eq(UserSign::getSignMonth, signMonth));
        MonthSignInfo monthSignInfo = new MonthSignInfo();
        // 本月用户尚未签到
        if(userSign == null) {
            monthSignInfo.setUserId(userId);
            monthSignInfo.setSignCount(0);
            return monthSignInfo;
        }
        Integer signRecord = userSign.getSignRecord();
        List<Integer> signDates = new ArrayList<>();
        for (int i = 1; i <= now.getDayOfMonth(); i++) {
            if((signRecord & (1 << i)) > 0) {
                signDates.add(i);
            }
        }
        monthSignInfo.setUserId(userId);
        monthSignInfo.setSignCount(userSign.getSignCount());
        monthSignInfo.setSignDates(signDates);
        return monthSignInfo;
    }

    @Override
    public boolean checkSign(String userId, Date date) {
        LocalDate localDate = DateUtil.date2LocalDate(date);
        // 校验当前是否已经签到
        String signMonth = localDate.format(formatter);
        UserSign userSign = this.baseMapper.selectOne(new QueryWrapper<UserSign>().lambda()
                .eq(UserSign::getUserKey, userId).eq(UserSign::getSignMonth, signMonth));
        // 当月并没有签到过
        if(userSign == null) {
            return false;
        }
        // 获取当前日期是否有签到
        int dayOfMonth = localDate.getDayOfMonth();
        int result = (1 << dayOfMonth) & userSign.getSignRecord();
        return (result > 0);
    }
}
