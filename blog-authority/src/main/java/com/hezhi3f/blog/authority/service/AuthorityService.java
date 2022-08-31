package com.hezhi3f.blog.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hezhi3f.blog.common.entity.authority.AuthorityPO;
import com.hezhi3f.blog.common.entity.user.UserPO;

import java.util.Optional;

public interface AuthorityService extends IService<UserPO> {


    UserPO verify(String token);

    String refresh(UserPO userPO);
}
