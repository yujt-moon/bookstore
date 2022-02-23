package com.moon.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moon.bookstore.api.entity.Ccer;
import com.moon.bookstore.api.service.ICcerService;
import com.moon.bookstore.service.mapper.CcerMapper;
import org.springframework.stereotype.Service;

/**
 * @author yujiangtao
 * @date 2021/12/30 下午4:02
 */
@Service
public class CcerServiceImpl extends ServiceImpl<CcerMapper, Ccer> implements ICcerService {
}
