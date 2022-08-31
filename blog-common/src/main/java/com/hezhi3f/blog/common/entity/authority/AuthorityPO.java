package com.hezhi3f.blog.common.entity.authority;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("blog_authority")
public class AuthorityPO {
    @TableField("user_id")
    private Long userId;
    private String secret;
}
