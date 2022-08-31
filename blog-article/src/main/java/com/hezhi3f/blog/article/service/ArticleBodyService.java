package com.hezhi3f.blog.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.blog.common.entity.article.ArticleBodyPO;

public interface ArticleBodyService extends IService<ArticleBodyPO> {
    ArticleBodyPO save(String content);

}
