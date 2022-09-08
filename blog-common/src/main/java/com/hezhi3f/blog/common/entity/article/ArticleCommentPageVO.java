package com.hezhi3f.blog.common.entity.article;

import lombok.Data;

import java.util.List;

@Data
public class ArticleCommentPageVO {
    private Long current;
    private Long pages;
    private Long total;
    private List<ArticleCommentVO> comments;
}
