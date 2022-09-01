package com.hezhi3f.blog.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.entity.user.*;

import java.util.Optional;

public interface UserService extends IService<UserPO> {

    UserPO login(UserLoginDTO userLoginDTO);

    UserPO signup(UserSignupDTO userSignupDTO);

    String update(UserUpdateDTO userUpdateDTO);

    UserInfoVO getInfo();

    Optional<UserPO> get(Long id);

    String getNicknameById(Long id);
}
