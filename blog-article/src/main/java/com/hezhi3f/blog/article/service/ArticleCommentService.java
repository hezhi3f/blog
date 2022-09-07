package com.hezhi3f.blog.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.blog.common.entity.article.ArticleCommentCreateDTO;
import com.hezhi3f.blog.common.entity.article.ArticleCommentDTO;
import com.hezhi3f.blog.common.entity.article.ArticleCommentPO;
import com.hezhi3f.blog.common.entity.article.ArticleCommentPageVO;

public interface ArticleCommentService extends IService<ArticleCommentPO> {
    ArticleCommentPageVO list(ArticleCommentDTO dto);
    void create(ArticleCommentCreateDTO dto);
}
