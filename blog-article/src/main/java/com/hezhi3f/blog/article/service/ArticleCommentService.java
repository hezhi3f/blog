package com.hezhi3f.blog.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.blog.common.entity.article.*;

public interface ArticleCommentService extends IService<ArticleCommentPO> {
    ArticleCommentPageVO list(ArticleCommentDTO dto);

    ArticleCommentVO create(ArticleCommentCreateDTO dto);
}
