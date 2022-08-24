package com.hezhi3f.bloguser.entity.user;

import com.hezhi3f.bloguser.validate.annotation.Length;
import com.hezhi3f.bloguser.validate.annotation.RegexEnum;
import com.hezhi3f.bloguser.validate.annotation.Required;
import com.hezhi3f.bloguser.validate.annotation.Type;
import lombok.Data;

@Data
public class UserUpdateDTO {
    @Required(msg = "修改用户的id不能为空")
    private Long id;
    private String gender;
    private String oldPassword;
    @Length(min = 8, max = 36, msg = "请输入长度为8到36位的密码")
    @Type(regex = RegexEnum.PASSWORD, msg = "密码只能为字母、数字、.~!@#$%^&*等特殊字符组成")
    private String newPassword;
    @Length(max = 32, msg = "昵称不能超过32个字符")
    private String nickname;
}
