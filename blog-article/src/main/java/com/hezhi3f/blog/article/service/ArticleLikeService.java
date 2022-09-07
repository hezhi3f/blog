package com.hezhi3f.blog.article.service;


public interface ArticleLikeService {
    Boolean isLike(Long articleId);

    void like(Long articleId);

    void dislike(Long articleId);

    Long count(Long articleId);
}
