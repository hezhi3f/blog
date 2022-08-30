package com.hezhi3f.blog.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.blog.common.entity.article.ArticleCreateDTO;
import com.hezhi3f.blog.common.entity.article.ArticlePO;
import com.hezhi3f.blog.common.entity.article.ArticleVO;
import com.hezhi3f.blog.common.entity.result.Result;

public interface ArticleService extends IService<ArticlePO> {
    Result<Void> create(ArticleCreateDTO articleCreateDTO);

    Result<ArticleVO> getArticleVOByArticleId(Long articleId);
}
