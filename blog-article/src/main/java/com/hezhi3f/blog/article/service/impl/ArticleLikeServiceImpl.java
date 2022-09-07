package com.hezhi3f.blog.article.service.impl;

import com.hezhi3f.blog.article.service.ArticleLikeService;
import com.hezhi3f.blog.common.context.UserContext;
import com.hezhi3f.blog.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleLikeServiceImpl implements ArticleLikeService {

    private final RedisService redisService;

    @Override
    public Boolean isLike(Long articleId) {
        return redisService.isArticleLike(articleId, UserContext.get().getId());
    }

    @Override
    public void like(Long articleId) {
        redisService.addArticleLike(articleId, UserContext.get().getId());
    }

    @Override
    public void dislike(Long articleId) {
        redisService.deleteArticleLike(articleId, UserContext.get().getId());
    }

    @Override
    public Long count(Long articleId) {
        return redisService.countArticleLike(articleId);
    }

    @Autowired
    public ArticleLikeServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }
}
