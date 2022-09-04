package com.hezhi3f.blog.authority.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hezhi3f.blog.authority.dao.AuthorityMapper;
import com.hezhi3f.blog.authority.service.AuthorityService;
import com.hezhi3f.blog.common.entity.user.UserPO;
import com.hezhi3f.blog.common.service.RedisService;
import com.hezhi3f.blog.common.util.Assert;
import com.hezhi3f.blog.common.util.CodeUtils;
import com.hezhi3f.blog.common.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AuthorityServiceImpl
        extends ServiceImpl<AuthorityMapper, UserPO> implements AuthorityService {

    @Autowired
    private RedisService redisService;

    @Override
    public UserPO verify(String token) {
        Assert.isNotNull(token, "认证信息token不存在，权限不足");
        DecodedJWT jwt = JWT.decode(token);
        Long id = jwt.getClaim("id").asLong();
        Assert.isNotNull(id, "认证信息不正确");
        UserPO po = redisService.getUser(id);
        if (po == null) {
            po = this.getById(id);
            Assert.isNotNull(po, "无效的token信息");
            redisService.setUser(po);
        }

        TokenUtils.verify(jwt, po.getSecret());
        return po;
    }

    @Override
    public String refresh(UserPO userPO) {
        userPO.setSecret(CodeUtils.uuid());
        userPO.setGmtModified(new Date());
        this.updateById(userPO);
        redisService.setUser(userPO);
        return TokenUtils.create(userPO);
    }

}
