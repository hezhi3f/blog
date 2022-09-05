package com.hezhi3f.blog.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.hezhi3f.blog.common.entity.user.UserPO;
import com.hezhi3f.blog.common.service.RedisPrefix;
import com.hezhi3f.blog.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    private final StringRedisTemplate template;


    @Override
    public void setUser(UserPO userPO) {
        log.info("redis缓存set:{}", userPO);
        template.opsForValue()
                .set(RedisPrefix.USER + userPO.getId(),
                        JSON.toJSONString(userPO));
    }

    @Override
    public UserPO getUser(Long id) {
        String str = template.opsForValue().get(RedisPrefix.USER + id);
        log.info("redis缓存get:{}", str);
        return JSON.parseObject(str, UserPO.class);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("redis缓存delete:{}", id);
        String key = RedisPrefix.USER + id;
        template.delete(key);
    }

    @Override
    public void set(String key, String value) {
        template.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, Duration timeout) {
        template.opsForValue().set(key, value, timeout);
    }

    @Override
    public String get(String key) {
        return template.opsForValue().get(key);
    }


    @Override
    public void delete(String email) {
        template.opsForValue().getAndDelete("blog:email:" + email);
    }


    public RedisServiceImpl(StringRedisTemplate template) {
        this.template = template;
    }
}
