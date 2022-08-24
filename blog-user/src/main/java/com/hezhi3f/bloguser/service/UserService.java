package com.hezhi3f.bloguser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.bloguser.entity.result.Result;
import com.hezhi3f.bloguser.entity.user.UserLoginDTO;
import com.hezhi3f.bloguser.entity.user.UserPO;

public interface UserService extends IService<UserPO> {

    Result<String> login(UserLoginDTO userLoginDTO);

    void insert(UserPO userPO);
}
