package com.moon.bookstore.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moon.bookstore.api.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yujiangtao
 * @date 2021/10/26 下午2:14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
