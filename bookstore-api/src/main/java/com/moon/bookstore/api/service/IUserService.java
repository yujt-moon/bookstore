package com.moon.bookstore.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moon.bookstore.api.entity.User;
import com.moon.bookstore.api.request.UserAddRequest;
import com.moon.bookstore.api.request.UserModifyRequest;
import com.moon.bookstore.api.request.UserPageRequest;

/**
 * @author yujiangtao
 * @date 2021/10/26 下午2:13
 */
public interface IUserService {

    IPage<User> getPage(UserPageRequest request);

    Long addUser(UserAddRequest request);

    boolean delUser(Long id);

    boolean modifyUser(UserModifyRequest request);
}
