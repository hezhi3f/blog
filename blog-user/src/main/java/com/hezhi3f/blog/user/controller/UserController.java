package com.hezhi3f.blog.user.controller;


import com.hezhi3f.blog.common.entity.user.*;
import com.hezhi3f.blog.user.service.UserService;

import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.util.ResultUtils;
import com.hezhi3f.blog.common.validate.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public Result<String> login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PostMapping("/signup")
    public Result<String> signup(@Validated @RequestBody UserSignupDTO userSignupDTO) {
        return userService.signup(userSignupDTO);
    }

    @PostMapping("/update")
    public Result<String> update(
            @Validated @RequestBody UserUpdateDTO userUpdateDTO,
            @RequestAttribute("id") Long id) {
        userUpdateDTO.setId(id);
        return userService.update(userUpdateDTO);
    }

    @PostMapping("/info")
    public Result<UserInfoVO> info(@RequestAttribute("id") Long id) {
        return userService.getInfo(id);
    }


    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Result<UserPO> get(@RequestParam Long id) {
        return userService.get(id);
    }

    @PostMapping("/test")
    public Result<Void> test(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        return ResultUtils.success();
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
