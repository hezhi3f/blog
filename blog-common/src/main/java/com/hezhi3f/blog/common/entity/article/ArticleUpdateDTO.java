package com.hezhi3f.blog.common.entity.article;

import lombok.Data;

import java.util.List;

@Data
public class ArticleUpdateDTO {
    private Long articleId;
    private Long userId;
    private String title;
    private String content;
    private String kind;
    private List<String> tags;
}
