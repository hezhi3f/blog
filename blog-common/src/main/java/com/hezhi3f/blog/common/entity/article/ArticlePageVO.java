package com.hezhi3f.blog.common.entity.article;

import lombok.Data;

import java.util.List;

@Data
public class ArticlePageVO {
    // 总页数
    private Long pages;
    // 每页大小
    private Long size;
    // 当前页数
    private Long current;
    // 记录总数
    private Long total;
    private List<ArticleVO> records;
}
