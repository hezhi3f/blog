package com.hezhi3f.bloguser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.common.entity.result.Result;
import com.hezhi3f.common.entity.user.*;

public interface UserService extends IService<UserPO> {

    Result<String> login(UserLoginDTO userLoginDTO);

    Result<String> signup(UserSignupDTO userSignupDTO);

    Result<String> update(UserUpdateDTO userUpdateDTO);

    Result<UserInfoVO> getInfo(Integer id);
}
