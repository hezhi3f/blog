package com.hezhi3f.blog.article.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blog.article.dao.ArticleKindMapper;
import com.hezhi3f.blog.article.service.ArticleKindService;
import com.hezhi3f.blog.common.entity.article.ArticleKindPO;
import com.hezhi3f.blog.common.util.Assert;
import org.springframework.stereotype.Service;

@Service
public class ArticleKindServiceImpl extends ServiceImpl<ArticleKindMapper, ArticleKindPO> implements ArticleKindService {
    @Override
    public ArticleKindPO save(String kind) {
        ArticleKindPO kindPO = this.getOne(Wrappers.<ArticleKindPO>query().eq("kind", kind));
        Assert.isNotNull(kindPO, "错误的分类参数");
        return kindPO;
    }
}
