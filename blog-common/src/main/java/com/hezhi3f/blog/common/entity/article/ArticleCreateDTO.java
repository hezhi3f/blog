package com.hezhi3f.blog.common.entity.article;

import com.hezhi3f.blog.common.validate.annotation.Length;
import com.hezhi3f.blog.common.validate.annotation.Required;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleCreateDTO {
    @Required(msg = "文章标题不能为空")
    @Length(min = 1, max = 64, msg = "标题不能超过64个字符")
    private String title;
    @Required(msg = "文章内容不能为空")
    private String content;
    @Required(msg = "文章分类不能为空")
    private String kind;
    private List<String> tags;
}
