package com.hezhi3f.blog.common.entity.article;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleCommentVO {
    private Long id;
    private Long articleId;
    private Long commentUserId;
    private String commentUserNickname;
    private Long toUserId;
    private String toUserNickname;
    private String content;
    private Long childCommentCount;
    private Date gmtCreated;
}
