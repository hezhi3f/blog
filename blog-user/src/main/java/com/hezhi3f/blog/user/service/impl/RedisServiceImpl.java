package com.hezhi3f.blog.user.service.impl;

import com.hezhi3f.blog.user.service.RedisService;
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

    @Override
    public String getCheckCode(String email) {
        ValueOperations<String, String> ops = template.opsForValue();
        return ops.get("blog:email:" + email);
    }

    @Override
    public void delete(String email) {
        template.opsForValue().getAndDelete("blog:email:" + email);
    }


    @Autowired
    public RedisServiceImpl(StringRedisTemplate template) {
        this.template = template;
    }
}
