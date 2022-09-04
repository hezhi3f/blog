package com.hezhi3f.blog.authority.controller;

import com.hezhi3f.blog.authority.service.AuthorityService;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.entity.user.UserPO;
import com.hezhi3f.blog.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    @RequestMapping("/authority/verify")
    public Result<UserPO> verify(@RequestBody String token) {
        UserPO po = authorityService.verify(token);
        return ResultUtils.success(po);
    }

    @RequestMapping("/authority/refresh")
    public Result<String> refresh(@RequestBody UserPO userPO) {
        String token = authorityService.refresh(userPO);
        return ResultUtils.success(token);
    }
}
