package com.hezhi3f.bloguser.service.impl;

import com.hezhi3f.bloguser.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    private final StringRedisTemplate template;


    @Override
    public void setCheckCode(String email, String checkCode) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set("blog:email:" + email, checkCode, 10, TimeUnit.MINUTES);
    }


    @Autowired
    public RedisServiceImpl(StringRedisTemplate template) {
        this.template = template;
    }
}
