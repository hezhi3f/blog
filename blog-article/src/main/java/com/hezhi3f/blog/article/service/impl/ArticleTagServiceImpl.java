package com.hezhi3f.blog.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blog.article.dao.ArticleTagMapper;
import com.hezhi3f.blog.article.service.ArticleTagService;
import com.hezhi3f.blog.common.entity.article.ArticleTagPO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleTagServiceImpl
        extends ServiceImpl<ArticleTagMapper, ArticleTagPO> implements ArticleTagService {
    @Override
    public void saveBatch(Long id, List<String> tags) {
        this.saveBatch(boxedTag(id, tags));
    }

    @NotNull
    private List<ArticleTagPO> boxedTag(Long id, List<String> tags) {
        return tags.stream().map(tag -> createTagPO(id, tag)).collect(Collectors.toList());
    }

    @NotNull
    private ArticleTagPO createTagPO(Long id, String tag) {
        ArticleTagPO po = new ArticleTagPO();
        po.setArticleId(id);
        po.setTag(tag);
        return po;
    }

    @Override
    public List<ArticleTagPO> getTags(Long articleId) {
        return this.list(Wrappers.<ArticleTagPO>query().eq("article_id", articleId));
    }

}
