package com.hezhi3f.blogarticle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.common.entity.article.ArticleBodyPO;

public interface ArticleBodyService extends IService<ArticleBodyPO> {
    ArticleBodyPO create(String content);

}
