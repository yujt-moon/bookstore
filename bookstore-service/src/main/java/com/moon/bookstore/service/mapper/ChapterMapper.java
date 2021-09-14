package com.moon.bookstore.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moon.bookstore.api.entity.Chapter;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yujiangtao
 * @date 2021/1/12 上午12:11
 */
@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {
}
