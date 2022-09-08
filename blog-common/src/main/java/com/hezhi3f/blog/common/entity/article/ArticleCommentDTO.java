package com.hezhi3f.blog.common.entity.article;

import com.hezhi3f.blog.common.validate.annotation.Required;
import lombok.Data;

@Data
public class ArticleCommentDTO {
    @Required(msg = "请求的文章id不能为空")
    private Long articleId;
    private Long superCommentId;
    @Required(msg = "不能请求空评论")
    private Long page;
}
