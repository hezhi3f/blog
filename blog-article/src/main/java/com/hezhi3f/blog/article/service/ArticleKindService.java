package com.hezhi3f.blog.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.blog.common.entity.article.ArticleKindPO;

public interface ArticleKindService extends IService<ArticleKindPO> {
    ArticleKindPO save(String kind);
}
