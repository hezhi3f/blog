package com.hezhi3f.blog.common.service;

import com.hezhi3f.blog.common.entity.user.UserPO;

import java.time.Duration;

public interface RedisService {

    void setUser(UserPO userPO);

    UserPO getUser(Long id);

    void deleteUser(Long id);

    void set(String key, String value);

    void set(String key, String value, Duration timeout);

    String get(String key);

    void delete(String key);
}
