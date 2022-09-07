package com.hezhi3f.blog.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blog.common.context.UserContext;
import com.hezhi3f.blog.common.entity.user.*;
import com.hezhi3f.blog.common.exception.BlogException;
import com.hezhi3f.blog.common.service.RedisPrefix;
import com.hezhi3f.blog.common.service.RedisService;
import com.hezhi3f.blog.common.util.Assert;
import com.hezhi3f.blog.user.dao.UserMapper;
import com.hezhi3f.blog.user.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {
    private final RedisService redisService;


    @Autowired
    public UserServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public UserPO login(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();

        UserPO userPO = this.getOne(Wrappers.<UserPO>query().eq("email", email));
        Assert.isNotNull(userPO, "该邮箱还未注册");

        if (userLoginDTO.getCaptcha() != null) {
            String captcha = redisService.getCaptcha(email);
            Assert.isEquals(captcha, userLoginDTO.getCaptcha(), "验证码错误");
            redisService.deleteCaptcha(email);
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

        String checkCode = redisService.getCaptcha(email);
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
        UserPO userPO = UserContext.get();

        List<String> list = new ArrayList<>(3);
        updatePassword(userUpdateDTO, userPO, list);
        updateGender(userUpdateDTO, userPO, list);
        updateNickname(userUpdateDTO, userPO, list);
        if (!list.isEmpty()) {
            userPO.setGmtModified(new Date());
            this.updateById(userPO);
            redisService.deleteUser(userPO.getId());
            return String.join(",", list) + "更新成功";
        }

        return "未做任何改变";
    }

    private void updateNickname(UserUpdateDTO userUpdateDTO, UserPO userPO, List<String> list) {
        String nickname = userUpdateDTO.getNickname();
        if (nickname != null) {
            if (!Objects.equals(nickname, userPO.getNickname())) {
                userPO.setNickname(nickname);
                list.add("昵称");
            }
        }
    }

    private void updateGender(UserUpdateDTO userUpdateDTO, UserPO userPO, List<String> list) {
        String gender = userUpdateDTO.getGender();
        if (gender != null) {
            Assert.isTrue(Gender.isLegal(gender), "不合法的性别参数");
            Integer g = Gender.getGenderInt(gender);
            if (!Objects.equals(g, userPO.getGender())) {
                userPO.setGender(g);
                list.add("性别");
            }
        }
    }

    private void updatePassword(UserUpdateDTO userUpdateDTO, UserPO userPO, List<String> list) {
        String oldPassword = userUpdateDTO.getOldPassword();
        String newPassword = userUpdateDTO.getNewPassword();

        if (!Objects.isNull(oldPassword) || !Objects.isNull(newPassword)) {
            Assert.isNotNull(oldPassword, "旧密码不能为空");
            Assert.isNotNull(newPassword, "新密码不能为空");
            Assert.isEquals(oldPassword, userPO.getPassword(), "旧密码错误");
            if (!Objects.equals(oldPassword, newPassword)) {
                userPO.setPassword(newPassword);
                list.add("密码");
            }
        }
    }

    @Override
    public UserInfoVO getInfo() {
        UserPO userPO = UserContext.get();
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

    @Override
    public String getNicknameById(Long id) {
        UserPO userPO = this.getOne(Wrappers.<UserPO>query().select("nickname").eq("id", id));
        return userPO.getNickname();
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
