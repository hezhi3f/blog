package com.hezhi3f.blog.user.service;

public interface RedisService {
    void setCheckCode(String email, String checkCode);

    String getCheckCode(String email);

    void delete(String email);
}
