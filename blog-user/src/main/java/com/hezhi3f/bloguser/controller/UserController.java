package com.hezhi3f.bloguser.controller;

import com.hezhi3f.bloguser.entity.result.Result;
import com.hezhi3f.bloguser.entity.user.UserInfoVO;
import com.hezhi3f.bloguser.entity.user.UserLoginDTO;
import com.hezhi3f.bloguser.entity.user.UserSignupDTO;
import com.hezhi3f.bloguser.entity.user.UserUpdateDTO;
import com.hezhi3f.bloguser.service.UserService;
import com.hezhi3f.bloguser.util.ResultUtils;
import com.hezhi3f.bloguser.validate.annotation.Validated;
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
    public Result<String> update(@Validated @RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.update(userUpdateDTO);
    }

    @PostMapping("/info")
    public Result<UserInfoVO> info(@RequestAttribute("id") Integer id) {
        return userService.getInfo(id);
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
