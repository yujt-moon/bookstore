package com.moon.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moon.bookstore.api.entity.User;
import com.moon.bookstore.api.request.UserAddRequest;
import com.moon.bookstore.api.request.UserModifyRequest;
import com.moon.bookstore.api.request.UserPageRequest;
import com.moon.bookstore.api.service.IUserService;
import com.moon.bookstore.service.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yujiangtao
 * @date 2021/10/26 下午2:15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> getPage(UserPageRequest request) {
        Page<User> page = new Page<>(request.getPageNo(), request.getPageSize());
        return userMapper.selectPage(page, new QueryWrapper<>());
    }

    @Override
    public Long addUser(UserAddRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public boolean delUser(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public boolean modifyUser(UserModifyRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return userMapper.updateById(user) > 0;
    }
}
