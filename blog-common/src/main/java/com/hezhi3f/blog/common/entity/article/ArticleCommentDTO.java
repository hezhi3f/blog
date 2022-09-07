package com.hezhi3f.blog.common.entity.article;

import lombok.Data;

@Data
public class ArticleCommentDTO {
    private Long articleId;
    private Long superCommentId;
    private Long current;
}
