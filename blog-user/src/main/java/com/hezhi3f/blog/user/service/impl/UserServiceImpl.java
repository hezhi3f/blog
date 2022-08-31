package com.hezhi3f.blog.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.entity.user.*;
import com.hezhi3f.blog.common.exception.BlogException;
import com.hezhi3f.blog.common.util.Assert;
import com.hezhi3f.blog.user.api.AuthorityService;
import com.hezhi3f.blog.user.dao.UserMapper;
import com.hezhi3f.blog.user.service.RedisService;
import com.hezhi3f.blog.user.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {
    private final RedisService redisService;

    private final AuthorityService authorityService;

    @Autowired
    public UserServiceImpl(RedisService redisService, AuthorityService authorityService) {
        this.redisService = redisService;
        this.authorityService = authorityService;
    }

    @Override
    public UserPO login(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();

        UserPO userPO = this.getOne(Wrappers.<UserPO>query().eq("email", email));
        Assert.isNotNull(userPO, "该邮箱还未注册");

        if (userLoginDTO.getCheckCode() != null) {
            String checkCode = redisService.getCheckCode(email);
            Assert.isEquals(checkCode, userLoginDTO.getCheckCode(), "验证码错误");
            redisService.delete(email);
        } else if (userLoginDTO.getPassword() != null) {
            Assert.isEquals(userLoginDTO.getPassword(), userPO.getPassword(), "密码错误");
        } else {
            // 前端传递的非法参数
            throw new BlogException("参数错误");
        }
        userPO.setGmtModified(new Date());

        this.updateById(userPO);

        return userPO;
    }

    @Override
    public UserPO signup(UserSignupDTO userSignupDTO) {
        String email = userSignupDTO.getEmail();
        UserPO userPO = this.getOne(Wrappers.<UserPO>query().eq("email", email));
        Assert.isNull(userPO, "邮箱已经被注册");

        String checkCode = redisService.getCheckCode(email);
        Assert.isEquals(checkCode, userSignupDTO.getCheckCode(), "验证码错误");

        String password = userSignupDTO.getPassword();
        String checkPassword = userSignupDTO.getCheckPassword();
        Assert.isEquals(password, checkPassword, "两次密码输入不一致");

        userPO = new UserPO();
        userPO.setEmail(email);
        userPO.setPassword(password);

        userPO.setNickname(getNickname(email));
        userPO.setGmtCreated(new Date());

        boolean save = this.save(userPO);
        Assert.isTrue(save, "注册失败");
        return userPO;
    }

    @Override
    public String update(UserUpdateDTO userUpdateDTO) {
        Long id = userUpdateDTO.getId();
        UserPO userPO = this.getById(id);

        Assert.isNotNull(userPO, "更新的用户id不存在");

        String oldPassword = userUpdateDTO.getOldPassword();
        String newPassword = userUpdateDTO.getNewPassword();

        List<String> list = new ArrayList<>(3);
        if (!Objects.isNull(oldPassword) || !Objects.isNull(newPassword)) {
            Assert.isNotNull(oldPassword, "旧密码不能为空");
            Assert.isNotNull(newPassword, "新密码不能为空");
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
            return String.join(",", list) + "更新成功";
        }

        return "未做任何改变";
    }

    @Override
    public UserInfoVO getInfo(Long id) {
        UserPO userPO = this.getById(id);
        UserInfoVO userInfoVO = new UserInfoVO();

        userInfoVO.setEmail(userPO.getEmail());
        userInfoVO.setNickname(userPO.getNickname());
        userInfoVO.setGender(Gender.getGenderStr(userPO.getGender()));
        userInfoVO.setPassword("*".repeat(userPO.getPassword().length()));
        userInfoVO.setGmtCreated(userPO.getGmtCreated());
        userInfoVO.setGmtModified(userPO.getGmtModified());
        return userInfoVO;
    }

    @Override
    public Optional<UserPO> get(Long id) {
        UserPO userPO = this.getById(id);

        return Optional.ofNullable(userPO);
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
