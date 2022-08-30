package com.hezhi3f.blog.common.entity.article;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleVO {
    private String nickName;
    private String title;
    private String kind;
    private String content;
    private List<String> tags;
    private Date gmtCreated;
    private Date gmtModified;
}
