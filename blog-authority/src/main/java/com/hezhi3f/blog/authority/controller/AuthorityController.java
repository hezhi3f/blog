package com.hezhi3f.blog.authority.controller;

import com.auth0.jwt.JWT;
import com.hezhi3f.blog.authority.service.UserService;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.entity.user.UserPO;
import com.hezhi3f.blog.common.util.Assert;
import com.hezhi3f.blog.common.util.ResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorityController {
    private UserService userService;

    @RequestMapping("/verify")
    public Result<Void> verify(String token) {
        Long id = JWT.decode(token).getClaim("id").asLong();
        Result<UserPO> result = userService.get(id);
        if (result.getOk()) {
            return ResultUtils.success();

        } else {
            // ??
            return ResultUtils.error(result.getCode(), result.getMsg());
        }
    }

}
