package com.hezhi3f.bloguser.controller;

import com.hezhi3f.bloguser.entity.result.Result;
import com.hezhi3f.bloguser.entity.user.UserLoginDTO;
import com.hezhi3f.bloguser.entity.user.UserPO;
import com.hezhi3f.bloguser.service.UserService;
import com.hezhi3f.bloguser.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);

    }


    @PostMapping("/delete")
    public String delete(UserPO userPO) {
        userPO.setDeleted(true);
        userPO.setGmtModified(new Date());
        userService.updateById(userPO);
        return "";
    }

    @PostMapping("/get")
    public Result<UserPO> get(UserPO userPO) {
        UserPO user = userService.getById(userPO.getId());
        return ResultUtils.success(user);
    }

    @PostMapping("/test")
    public Result<Void> test(@RequestBody UserLoginDTO userLoginDTO) {
        return ResultUtils.success();
    }


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
