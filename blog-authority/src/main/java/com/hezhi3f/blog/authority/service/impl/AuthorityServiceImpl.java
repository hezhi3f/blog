package com.hezhi3f.blog.authority.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blog.authority.dao.AuthorityMapper;
import com.hezhi3f.blog.authority.service.AuthorityService;
import com.hezhi3f.blog.common.entity.authority.AuthorityPO;
import com.hezhi3f.blog.common.entity.user.UserPO;
import com.hezhi3f.blog.common.util.Assert;
import com.hezhi3f.blog.common.util.CodeUtils;
import com.hezhi3f.blog.common.util.TokenUtils;
import org.springframework.stereotype.Service;


@Service
public class AuthorityServiceImpl
        extends ServiceImpl<AuthorityMapper, UserPO> implements AuthorityService {

    @Override
    public UserPO verify(String token) {
        Assert.isNotNull(token, "认证信息token不存在，权限不足");
        DecodedJWT jwt = JWT.decode(token);
        Long id = jwt.getClaim("id").asLong();
        Assert.isNotNull(id, "认证信息不正确");
        UserPO userPO = this.getById(id);
        Assert.isNotNull(userPO, "无效的token信息");

        TokenUtils.verify(jwt, userPO.getSecret());
        return userPO;
    }

    @Override
    public String refresh(UserPO userPO) {
        userPO.setSecret(CodeUtils.uuid());

        return TokenUtils.create(userPO);

    }

}
