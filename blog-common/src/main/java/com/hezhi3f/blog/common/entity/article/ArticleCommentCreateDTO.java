package com.hezhi3f.blog.common.entity.article;

import com.hezhi3f.blog.common.validate.annotation.Range;
import com.hezhi3f.blog.common.validate.annotation.Required;
import lombok.Data;

@Data
public class ArticleCommentCreateDTO {
    @Required(msg = "文章id不能为空")
    private Long articleId;
    private Long superCommentId;
    private Long toUserId;
    @Required(msg = "不能发表空评论")
    @Range(min = 1, max = 128, msg = "评论字数为1到128个字符")
    private String content;
}
