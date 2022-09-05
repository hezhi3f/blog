package com.hezhi3f.blog.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.blog.common.entity.article.ArticleCreateDTO;
import com.hezhi3f.blog.common.entity.article.ArticlePO;
import com.hezhi3f.blog.common.entity.article.ArticleUpdateDTO;
import com.hezhi3f.blog.common.entity.article.ArticleVO;
import com.hezhi3f.blog.common.entity.result.Result;

import java.util.List;

public interface ArticleService extends IService<ArticlePO> {
    void create(ArticleCreateDTO articleCreateDTO);

    ArticleVO getArticleVOByArticleId(Long articleId);

    List<ArticleVO> listByUserId(Long userId);

    String update(ArticleUpdateDTO articleUpdateDTO);
}
