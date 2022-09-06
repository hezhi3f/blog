package com.hezhi3f.blog.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.blog.common.entity.article.*;

import java.util.List;

public interface ArticleService extends IService<ArticlePO> {
    void create(ArticleCreateDTO articleCreateDTO);

    ArticleVO getArticleVOByArticleId(Long articleId);

    List<ArticleVO> listByUserId(Long userId);

    String update(ArticleUpdateDTO articleUpdateDTO);

    ArticlePageVO main(ArticleMainDTO dto);
}
