package com.hezhi3f.common.entity.user;

import com.hezhi3f.common.validate.annotation.Length;
import com.hezhi3f.common.validate.annotation.RegexEnum;
import com.hezhi3f.common.validate.annotation.Required;
import com.hezhi3f.common.validate.annotation.Type;
import lombok.Data;

@Data
public class UserEmailDTO {
    @Required(msg = "邮箱不能为空")
    @Type(regex = RegexEnum.EMAIL, msg = "邮箱格式不正确")
    @Length(min = 6, max = 36, msg = "请输入长度为6到36位的邮箱")
    private String email;
}
