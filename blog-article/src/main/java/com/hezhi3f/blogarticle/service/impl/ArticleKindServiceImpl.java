package com.hezhi3f.blogarticle.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blogarticle.dao.ArticleKindMapper;
import com.hezhi3f.blogarticle.service.ArticleKindService;
import com.hezhi3f.common.entity.article.ArticleKindPO;
import org.springframework.stereotype.Service;

@Service
public class ArticleKindServiceImpl extends ServiceImpl<ArticleKindMapper, ArticleKindPO> implements ArticleKindService {
    @Override
    public ArticleKindPO create(String kind) {
        ArticleKindPO kindPO = this.getOne(Wrappers.<ArticleKindPO>query().eq("kind", kind));
        // todo not null

        return kindPO;
    }
}
