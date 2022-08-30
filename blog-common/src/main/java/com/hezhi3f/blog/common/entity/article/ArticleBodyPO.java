package com.hezhi3f.blog.common.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("blog_article_body")
public class ArticleBodyPO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String content;
}
