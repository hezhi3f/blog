package com.hezhi3f.blog.common.entity.article;

import com.hezhi3f.blog.common.validate.annotation.Range;
import lombok.Data;

@Data
public class ArticleMainDTO {
    @Range(min = 1)
    private Long page;
    @Range(min = 1,max = 100)
    private Long size;
}
