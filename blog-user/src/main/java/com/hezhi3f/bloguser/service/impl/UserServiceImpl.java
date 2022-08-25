package com.hezhi3f.bloguser.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.bloguser.dao.UserMapper;
import com.hezhi3f.bloguser.entity.result.Result;
import com.hezhi3f.bloguser.entity.user.*;
import com.hezhi3f.bloguser.exception.BlogUserException;
import com.hezhi3f.bloguser.service.UserService;
import com.hezhi3f.bloguser.util.Assert;
import com.hezhi3f.bloguser.util.CodeUtils;
import com.hezhi3f.bloguser.util.ResultUtils;
import com.hezhi3f.bloguser.util.TokenUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {
    @Override
    public Result<String> login(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();

        UserPO userPO = this.getOne(Wrappers.<UserPO>query().eq("email", email));
        Assert.notNull(userPO, "该邮箱还未注册");

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

        this.updateById(userPO);
        String token = TokenUtils.create(userPO);
        return ResultUtils.success(token);
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

    @Override
    public Result<String> update(UserUpdateDTO userUpdateDTO) {
        Long id = userUpdateDTO.getId();
        UserPO userPO = this.getById(id);

        Assert.notNull(userPO, "更新的用户id不存在");

        String oldPassword = userUpdateDTO.getOldPassword();
        String newPassword = userUpdateDTO.getNewPassword();

        List<String> list = new ArrayList<>(3);
        if (!Objects.isNull(oldPassword) || !Objects.isNull(newPassword)) {
            Assert.notNull(oldPassword, "旧密码不能为空");
            Assert.notNull(newPassword, "新密码不能为空");
            Assert.isEquals(oldPassword, userPO.getPassword(), "旧密码错误");
            if (!Objects.equals(oldPassword, newPassword)) {
                userPO.setPassword(newPassword);
                list.add("密码");
            }
        }

        String gender = userUpdateDTO.getGender();
        if (gender != null) {
            Assert.isTrue(Gender.isLegal(gender), "不合法的性别参数");
            Integer g = Gender.getGenderInt(gender);
            if (!Objects.equals(g, userPO.getGender())) {
                userPO.setGender(g);
                list.add("性别");
            }
        }

        String nickname = userUpdateDTO.getNickname();
        if (nickname != null) {
            if (!Objects.equals(nickname, userPO.getNickname())) {
                userPO.setNickname(nickname);
                list.add("昵称");
            }
        }

        if (!list.isEmpty()) {
            userPO.setGmtModified(new Date());
            this.updateById(userPO);
            return ResultUtils.success(String.join(",", list) + "更新成功");
        }

        return ResultUtils.success("未做任何改变");
    }

    @Override
    public Result<UserInfoVO> getInfo(Integer id) {
        UserPO userPO = this.getById(id);
        UserInfoVO userInfoVO = new UserInfoVO();

        userInfoVO.setEmail(userPO.getEmail());
        userInfoVO.setNickname(userPO.getNickname());
        userInfoVO.setGender(Gender.getGenderStr(userPO.getGender()));
        userInfoVO.setPassword("*".repeat(userPO.getPassword().length()));
        userInfoVO.setGmtCreated(userPO.getGmtCreated());
        userInfoVO.setGmtModified(userPO.getGmtModified());
        return ResultUtils.success(userInfoVO);
    }

    @NotNull
    private String getNickname(String email) {
        return "user_" + email.split("@")[0];
    }

    private static class Gender {
        private static final List<String> GENDER = Arrays.asList("未知", "男", "女");

        public static boolean isLegal(String gender) {
            return GENDER.contains(gender);
        }

        public static Integer getGenderInt(String gender) {
            return GENDER.indexOf(gender);
        }

        public static String getGenderStr(Integer gender) {
            return GENDER.get(gender);
        }
    }
}
