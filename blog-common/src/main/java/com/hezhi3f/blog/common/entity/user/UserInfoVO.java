package com.hezhi3f.blog.common.entity.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVO {
    public String email;
    public String nickname;
    public String gender;
    public String password;
    public Date gmtCreated;
    public Date gmtModified;
}
