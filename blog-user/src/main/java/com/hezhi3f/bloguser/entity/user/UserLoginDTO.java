package com.hezhi3f.bloguser.entity.user;


import com.hezhi3f.bloguser.validate.annotation.Length;
import com.hezhi3f.bloguser.validate.annotation.Required;
import com.hezhi3f.bloguser.validate.annotation.Type;
import lombok.Data;

@Data
public class UserLoginDTO {
    @Required(msg = "邮箱不能为空")
    @Type(regex = "[a-zA-Z\\d]+@[a-zA-Z\\d]+\\.[a-zA-Z\\d]+", msg = "邮箱格式不正确")
    @Length(min = 6, max = 32, msg = "请输入长度为6到32位的邮箱")
    private String email;

    @Length(min = 8, max = 32, msg = "请输入长度为8到32位的密码")
    @Type(regex = "^[a-zA-Z\\d\\.~!@#$%^&*]+$", msg = "密码只能为字母、数字、.~!@#$%^&*等特殊字符组成")
    private String password;

    @Length(min = 6,max = 6,msg = "验证码长度只能为6位")
    @Type(regex = "\\d+", msg = "验证码为纯数字")
    private String checkCode;
}
