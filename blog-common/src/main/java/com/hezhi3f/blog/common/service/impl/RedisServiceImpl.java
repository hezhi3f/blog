package com.hezhi3f.blog.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.hezhi3f.blog.common.entity.user.UserPO;
import com.hezhi3f.blog.common.service.RedisPrefix;
import com.hezhi3f.blog.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    private final StringRedisTemplate template;


    @Override
    public void setUser(UserPO userPO) {
        template.opsForValue()
                .set(RedisPrefix.USER + userPO.getId(),
                        JSON.toJSONString(userPO));
    }

    @Override
    public UserPO getUser(Long id) {
        String str = template.opsForValue().get(RedisPrefix.USER + id);
        return JSON.parseObject(str, UserPO.class);
    }

    @Override
    public void deleteUser(Long id) {
        String key = RedisPrefix.USER + id;
        template.delete(key);
    }

    public void addArticleLike(Long articleId, Long userId) {
        template.opsForSet().add(RedisPrefix.ARTICLE_LIKE + articleId, String.valueOf(userId));
    }

    public void deleteArticleLike(Long articleId, Long userId) {
        template.opsForSet().remove(RedisPrefix.ARTICLE_LIKE + articleId, String.valueOf(userId));
    }

    public Long countArticleLike(Long articleId) {
        return template.opsForSet().size(RedisPrefix.ARTICLE_LIKE + articleId);
    }

    @Override
    public Boolean isArticleLike(Long articleId, Long userId) {
        return template.opsForSet().isMember(RedisPrefix.ARTICLE_LIKE + articleId, String.valueOf(userId));
    }

    @Override
    public void deleteCaptcha(String email) {
        template.delete(RedisPrefix.CAPTCHA + email);
    }

    @Override
    public String getCaptcha(String email) {
        return template.opsForValue().get(RedisPrefix.CAPTCHA + email);
    }


    @Override
    public void setCaptcha(String email, String captcha, Duration timeout) {
        template.opsForValue().set(RedisPrefix.CAPTCHA + email, captcha, timeout);
    }

    @Override
    public void setCaptcha(String email, String captcha) {
        this.setCaptcha(email, captcha, Duration.ofMinutes(10));
    }


    public RedisServiceImpl(StringRedisTemplate template) {
        this.template = template;
    }
}
