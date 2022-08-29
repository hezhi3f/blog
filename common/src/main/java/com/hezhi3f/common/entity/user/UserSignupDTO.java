package com.hezhi3f.common.entity.user;

import com.hezhi3f.common.validate.annotation.Length;
import com.hezhi3f.common.validate.annotation.RegexEnum;
import com.hezhi3f.common.validate.annotation.Required;
import com.hezhi3f.common.validate.annotation.Type;
import lombok.Data;

@Data
public class UserSignupDTO {
    @Required(msg = "邮箱不能为空")
    @Type(regex = RegexEnum.EMAIL, msg = "邮箱格式不正确")
    @Length(min = 6, max = 36, msg = "请输入长度为6到36位的邮箱")
    private String email;
    @Required(msg = "密码不能为空")
    @Length(min = 6, max = 36, msg = "请输入长度为6到36位的密码")
    @Type(regex = RegexEnum.PASSWORD, msg = "密码只能为字母、数字、.~!@#$%^&*等特殊字符组成")
    private String password;
    @Required(msg = "密码不能为空")
    @Length(min = 6, max = 36, msg = "请输入长度为6到36位的密码")
    @Type(regex = RegexEnum.PASSWORD, msg = "密码只能为字母、数字、.~!@#$%^&*等特殊字符组成")
    private String checkPassword;
    @Required(msg = "验证码不能为空")
    @Length(min = 6, max = 6, msg = "验证码长度只能为6位")
    @Type(regex = RegexEnum.NUMBER, msg = "验证码为纯数字")
    private String checkCode;
}
