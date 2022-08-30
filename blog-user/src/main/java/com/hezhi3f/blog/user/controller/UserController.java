package com.hezhi3f.blog.user.controller;


import com.hezhi3f.blog.common.entity.user.*;
import com.hezhi3f.blog.common.exception.BlogException;
import com.hezhi3f.blog.user.service.UserService;

import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.util.ResultUtils;
import com.hezhi3f.blog.common.validate.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public Result<String> login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        String token = userService.login(userLoginDTO);
        return ResultUtils.success(token);
    }

    @PostMapping("/signup")
    public Result<String> signup(@Validated @RequestBody UserSignupDTO userSignupDTO) {
        String token = userService.signup(userSignupDTO);
        return ResultUtils.success(token);
    }

    @PostMapping("/update")
    public Result<String> update(
            @Validated @RequestBody UserUpdateDTO userUpdateDTO,
            @RequestAttribute("id") Long id) {
        userUpdateDTO.setId(id);
        String updateInfo = userService.update(userUpdateDTO);
        return ResultUtils.success(updateInfo);
    }

    @PostMapping("/info")
    public Result<UserInfoVO> info(@RequestAttribute("id") Long id) {
        UserInfoVO vo = userService.getInfo(id);
        return ResultUtils.success(vo);
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result<UserPO> get(@RequestParam Long id) {
        UserPO userPO = userService.get(id).orElseThrow(() -> new BlogException("用户不存在"));
        return ResultUtils.success(userPO);
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
