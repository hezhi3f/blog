package com.hezhi3f.common.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("blog_article")
public class ArticlePO {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Long userId;
    private String title;
    @TableField("article_kind_id")
    private Integer articleKindId;
    @TableField("article_body_id")
    private Long articleBodyId;
    private Date gmtCreated;
    private Date gmtModified;
    private Boolean deleted;
}
