package com.hezhi3f.bloguser.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.bloguser.dao.UserMapper;
import com.hezhi3f.bloguser.entity.result.Result;
import com.hezhi3f.bloguser.entity.user.UserLoginDTO;
import com.hezhi3f.bloguser.entity.user.UserPO;
import com.hezhi3f.bloguser.exception.BlogUserException;
import com.hezhi3f.bloguser.service.UserService;
import com.hezhi3f.bloguser.util.ResultUtils;
import com.hezhi3f.bloguser.util.UuidUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {
    @Override
    public Result<String> login(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();
        String checkCode = userLoginDTO.getCheckCode();
        String password = userLoginDTO.getPassword();

        UserPO userPO = this.getOne(Wrappers.<UserPO>query().eq("email", email));

        // 是否已经注册了
        if (userPO == null) {
            // 未注册，去注册
            userPO = register(userLoginDTO);
            // todo
        } else {
            return login(userLoginDTO, userPO);
        }
        return null;
    }

    @NotNull
    private Result<String> login(UserLoginDTO userLoginDTO, UserPO userPO) {
        String checkCode = userLoginDTO.getCheckCode();
        String password = userLoginDTO.getPassword();
        // 已注册，去登录
        if (checkCode != null) {
            // 使用redis缓存获取校验码比对
            String checkCodeRedis = "123456";
            if (!Objects.equals(checkCodeRedis, checkCode)) {
                throw new BlogUserException("验证码错误");
            }
        } else if (password != null) {
            if (!Objects.equals(password, userPO.getPassword())) {
                throw new BlogUserException("邮箱或者密码错误");
            }
        } else {
            throw new BlogUserException("验证码和密码必须存在一个");
        }

        // 身份校验成功
        userPO.setSecret(UuidUtils.uuid());
        userPO.setGmtModified(new Date());
        boolean update = this.updateById(userPO);
        if (!update) {
            throw new BlogUserException("更新失败");
        }

        // 生成token
        // String token = TokenUtils.create(userPO);
        String token = "ngouengdsgosofnf2n94nfdof98nrjef";
        return ResultUtils.success(token);
    }

    private UserPO register(UserLoginDTO userLoginDTO) {
        UserPO userPO = new UserPO();
        String email = userLoginDTO.getEmail();

        userPO.setEmail(email);
        userPO.setNickname(getNickname(email));
        userPO.setGmtCreated(new Date());
        userPO.setPassword(userLoginDTO.getPassword());

        boolean save = this.save(userPO);

        if (!save) {
            throw new BlogUserException("注册失败");
        }
        return userPO;
    }

    @NotNull
    private String getNickname(String email) {
        return "user_" + email.split("@")[0];
    }

    @Override
    public void insert(UserPO userPO) {
        userPO.setGmtCreated(new Date());
        if (userPO.getNickname() != null) {
            userPO.setNickname(getNickname(userPO.getEmail()));
        }

        userPO.setDeleted(false);

    }
}
