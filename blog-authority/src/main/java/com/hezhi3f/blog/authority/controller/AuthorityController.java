package com.hezhi3f.blog.authority.controller;

import com.auth0.jwt.JWT;
import com.hezhi3f.blog.authority.service.UserService;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.entity.user.UserPO;
import com.hezhi3f.blog.common.util.Assert;
import com.hezhi3f.blog.common.util.ResultUtils;
import com.hezhi3f.blog.common.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorityController {
    @Autowired
    private UserService userService;

    @RequestMapping("/verify")
    public Result<Boolean> verify(String token) {
        Long id = TokenUtils.getAsLong(token, "id");
        Result<UserPO> userPO = userService.get(id);
        Boolean ok = userPO.getOk();

        return ResultUtils.success(ok);
    }

}
