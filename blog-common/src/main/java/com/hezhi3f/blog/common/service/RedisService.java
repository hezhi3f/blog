package com.hezhi3f.blog.common.service;

import com.hezhi3f.blog.common.entity.user.UserPO;

import java.time.Duration;

public interface RedisService {

    void setUser(UserPO userPO);

    UserPO getUser(Long id);

    void deleteUser(Long id);

    void addArticleLike(Long articleId, Long userId);

    void deleteArticleLike(Long articleId, Long userId);

    Long countArticleLike(Long articleId);

    Boolean isArticleLike(Long articleId, Long userId);

    void deleteCaptcha(String email);

    String getCaptcha(String email);

    void setCaptcha(String email, String captcha, Duration timeout);

    void setCaptcha(String email, String captcha);
}
