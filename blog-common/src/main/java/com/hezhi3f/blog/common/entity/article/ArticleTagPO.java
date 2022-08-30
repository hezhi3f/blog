package com.hezhi3f.blog.common.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("blog_article_tag")
public class ArticleTagPO {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("article_id")
    private Long articleId;
    private String tag;
}
