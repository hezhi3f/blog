package com.hezhi3f.blog.user.controller;


import com.hezhi3f.blog.common.context.UserContext;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.entity.user.*;
import com.hezhi3f.blog.common.exception.BlogException;
import com.hezhi3f.blog.common.util.ResultUtils;
import com.hezhi3f.blog.common.validate.annotation.Validated;
import com.hezhi3f.blog.user.api.AuthorityService;
import com.hezhi3f.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthorityService authorityService;

    @PostMapping("/login")
    public Result<String> login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        UserPO userPO = userService.login(userLoginDTO);
        return authorityService.refresh(userPO);
    }

    @PostMapping("/signup")
    public Result<String> signup(@Validated @RequestBody UserSignupDTO userSignupDTO) {
        UserPO userPO = userService.signup(userSignupDTO);
        return authorityService.refresh(userPO);
    }

    @PostMapping("/update")
    public Result<String> update(
            @Validated @RequestBody UserUpdateDTO userUpdateDTO) {
        String updateInfo = userService.update(userUpdateDTO);
        return ResultUtils.success(updateInfo);
    }

    @PostMapping("/info")
    public Result<UserInfoVO> info() {
        UserInfoVO vo = userService.getInfo();
        return ResultUtils.success(vo);
    }


    @PostMapping("/get")
    public Result<UserPO> get(@RequestParam Long id) {
        UserPO userPO = userService.get(id).orElseThrow(() -> new BlogException("用户不存在"));
        return ResultUtils.success(userPO);
    }

    @RequestMapping("/nickname")
    public Result<String> getNickname(@RequestParam Long id) {
        String nickname = userService.getNicknameById(id);
        return ResultUtils.success(nickname);
    }

    @Autowired
    public UserController(UserService userService, AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }
}
