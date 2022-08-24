package com.hezhi3f.bloguser.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("blog_user")
public class UserPO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private Integer gender;
    private String secret;
    @TableField("gmt_created")
    private Date gmtCreated;
    @TableField("gmt_modified")
    private Date gmtModified;
    @TableField("is_deleted")
    private Boolean deleted;
}
