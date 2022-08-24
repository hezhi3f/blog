package com.hezhi3f.bloguser.controller;

import com.hezhi3f.bloguser.entity.result.Result;
import com.hezhi3f.bloguser.entity.user.UserEmailDTO;
import com.hezhi3f.bloguser.service.RedisService;
import com.hezhi3f.bloguser.util.CodeUtils;
import com.hezhi3f.bloguser.util.ResultUtils;
import com.hezhi3f.bloguser.validate.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkCode")
public class CheckCodeController {
    private final RedisService redisService;

    @PostMapping("/checkEmail")
    public Result<String> checkEmail(@Validated @RequestBody UserEmailDTO userEmailDTO) {
        String email = userEmailDTO.getEmail();
        System.out.println(email);
        String checkCode = CodeUtils.num6();

        redisService.setCheckCode(email, checkCode);
        // send email
        
        return ResultUtils.success(checkCode);
    }

    @Autowired
    public CheckCodeController(RedisService redisService) {
        this.redisService = redisService;
    }
}
