package com.hezhi3f.blog.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.entity.user.*;

import java.util.Optional;

public interface UserService extends IService<UserPO> {

    String login(UserLoginDTO userLoginDTO);

    String signup(UserSignupDTO userSignupDTO);

    String update(UserUpdateDTO userUpdateDTO);

    UserInfoVO getInfo(Long id);

    Optional<UserPO> get(Long id);
}
