package com.hezhi3f.blog.common.entity.article;

import lombok.Data;

@Data
public class ArticleCommentCreateDTO {
    private Long articleId;
    private Long superCommentId;
    private Long toUserId;
    private String content;
}
