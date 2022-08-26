package com.hezhi3f.blogarticle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.common.entity.article.ArticleTagPO;

import java.util.List;

public interface ArticleTagService extends IService<ArticleTagPO> {
    void save(Long id, List<String> tags);

    List<String> getTags(Long articleId);
}
