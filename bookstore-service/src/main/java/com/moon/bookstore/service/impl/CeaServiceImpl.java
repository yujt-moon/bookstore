package com.moon.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moon.bookstore.api.entity.Cea;
import com.moon.bookstore.api.service.ICeaService;
import com.moon.bookstore.service.mapper.CeaMapper;
import org.springframework.stereotype.Service;

/**
 * @author yujiangtao
 * @date 2021/12/30 下午4:02
 */
@Service
public class CeaServiceImpl extends ServiceImpl<CeaMapper, Cea> implements ICeaService {
}
