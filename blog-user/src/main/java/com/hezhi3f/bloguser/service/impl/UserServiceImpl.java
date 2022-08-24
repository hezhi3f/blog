package com.hezhi3f.bloguser.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.bloguser.dao.UserMapper;
import com.hezhi3f.bloguser.entity.result.Result;
import com.hezhi3f.bloguser.entity.user.UserLoginDTO;
import com.hezhi3f.bloguser.entity.user.UserPO;
import com.hezhi3f.bloguser.entity.user.UserSignupDTO;
import com.hezhi3f.bloguser.exception.BlogUserException;
import com.hezhi3f.bloguser.service.UserService;
import com.hezhi3f.bloguser.util.Assert;
import com.hezhi3f.bloguser.util.ResultUtils;
import com.hezhi3f.bloguser.util.TokenUtils;
import com.hezhi3f.bloguser.util.CodeUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {
    @Override
    public Result<String> login(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();

        UserPO userPO = this.getOne(Wrappers.<UserPO>query().eq("email", email));
        Assert.isNotNull(userPO, "该邮箱还未注册");

        if (userLoginDTO.getCheckCode() != null) {
            String checkCode = "123456";
            Assert.isEquals(checkCode, userLoginDTO.getCheckCode(), "验证码错误");
        } else if (userLoginDTO.getPassword() != null) {
            Assert.isEquals(userLoginDTO.getPassword(), userPO.getPassword(), "密码错误");
        } else {
            // 前端传递的非法参数
            throw new BlogUserException("参数错误");
        }

        userPO.setGmtModified(new Date());
        userPO.setSecret(CodeUtils.uuid());

        String token = TokenUtils.create(userPO);
        return ResultUtils.success(token);
    }

    @NotNull
    private String getNickname(String email) {
        return "user_" + email.split("@")[0];
    }

    @Override
    public Result<String> signup(UserSignupDTO userSignupDTO) {
        String checkCode = "123456";
        Assert.isEquals(checkCode, userSignupDTO.getCheckCode(), "验证码错误");

        String email = userSignupDTO.getEmail();

        UserPO userPO = this.getOne(Wrappers.<UserPO>query().eq("email", email));

        Assert.isNull(userPO, "邮箱已经被注册");

        userPO = new UserPO();
        userPO.setEmail(email);
        userPO.setPassword(userSignupDTO.getPassword());


        userPO.setNickname(getNickname(email));
        userPO.setGmtCreated(new Date());
        userPO.setSecret(CodeUtils.uuid());

        boolean save = this.save(userPO);
        Assert.isTrue(save, "注册失败");

        String token = TokenUtils.create(userPO);
        return ResultUtils.success(token);
    }
}
