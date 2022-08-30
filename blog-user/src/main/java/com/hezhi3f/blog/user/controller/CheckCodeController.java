package com.hezhi3f.blog.user.controller;

import com.hezhi3f.blog.user.service.RedisService;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.entity.user.UserEmailDTO;
import com.hezhi3f.blog.common.util.CodeUtils;
import com.hezhi3f.blog.common.util.ResultUtils;
import com.hezhi3f.blog.common.validate.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
public class CheckCodeController {
    private final RedisService redisService;

    @PostMapping("/email")
    public Result<String> email(@Validated @RequestBody UserEmailDTO userEmailDTO) {
        String email = userEmailDTO.getEmail();
        String checkCode = CodeUtils.num6();

        redisService.setCheckCode(email, checkCode);
        // todo send email
        
        return ResultUtils.success();
    }

    @Autowired
    public CheckCodeController(RedisService redisService) {
        this.redisService = redisService;
    }
}
