package com.hezhi3f.blog.captcha.controller;

import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.service.RedisService;
import com.hezhi3f.blog.common.util.CodeUtils;
import com.hezhi3f.blog.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    private final RedisService redisService;

    @RequestMapping("/setEmail")
    public Result<Void> setEmail(@RequestParam("email") String email) {
        String captcha = CodeUtils.num6();

        // todo send email
        // send email
        redisService.setCaptcha(email, captcha);
        return ResultUtils.success();
    }

    @RequestMapping("/getEmail")
    public Result<String> getEmail(@RequestParam("email") String email) {
        String captcha = redisService.getCaptcha(email);
        return ResultUtils.success(captcha);
    }

    @Autowired
    public CaptchaController(RedisService redisService) {
        this.redisService = redisService;
    }
}
