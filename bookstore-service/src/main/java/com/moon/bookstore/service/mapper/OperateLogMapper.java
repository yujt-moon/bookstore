package com.moon.bookstore.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moon.bookstore.api.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yujiangtao
 * @date 2021/9/14 下午5:48
 */
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLog> {
}
