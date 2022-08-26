package com.hezhi3f.blogarticle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hezhi3f.common.entity.article.ArticlePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticlePO> {
}
