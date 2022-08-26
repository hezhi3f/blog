package com.hezhi3f.blogarticle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.common.entity.article.ArticleKindPO;

public interface ArticleKindService extends IService<ArticleKindPO> {
    ArticleKindPO create(String kind);
}
