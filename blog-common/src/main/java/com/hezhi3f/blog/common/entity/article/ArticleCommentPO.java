package com.hezhi3f.blog.common.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("blog_article_comment")
@Data
public class ArticleCommentPO {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Long userId;
    @TableField("super_comment_id")
    private Long superCommentId;
    @TableField("article_id")
    private Long articleId;
    private String content;
    @TableField("gmt_created")
    private Date gmtCreated;
    @TableField("id_deleted")
    private Boolean deleted;
}
