package com.hezhi3f.bloguser.service;

public interface RedisService {
    void setCheckCode(String email, String checkCode);
}
