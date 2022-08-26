package com.hezhi3f.common.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("blog_article_kind")
public class ArticleKindPO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String kind;
}
