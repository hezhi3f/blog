package com.hezhi3f.blog.common.entity.user;

import com.hezhi3f.blog.common.validate.annotation.Length;
import com.hezhi3f.blog.common.validate.annotation.RegexEnum;
import com.hezhi3f.blog.common.validate.annotation.Type;
import lombok.Data;

@Data
public class UserUpdateDTO {
    private String gender;
    private String oldPassword;
    @Length(min = 6, max = 36, msg = "请输入长度为6到36位的密码")
    @Type(regex = RegexEnum.PASSWORD, msg = "密码只能为字母、数字、.~!@#$%^&*等特殊字符组成")
    private String newPassword;
    @Length(max = 32, msg = "昵称不能超过32个字符")
    private String nickname;
}
