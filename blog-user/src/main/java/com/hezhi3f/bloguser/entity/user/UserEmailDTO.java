package com.hezhi3f.bloguser.entity.user;

import com.hezhi3f.bloguser.validate.annotation.*;
import lombok.Data;

@Data
public class UserEmailDTO {
    @Required(msg = "邮箱不能为空")
    @Type(regex = RegexEnum.EMAIL, msg = "邮箱格式不正确")
    @Length(min = 6, max = 36, msg = "请输入长度为6到36位的邮箱")
    private String email;
}
