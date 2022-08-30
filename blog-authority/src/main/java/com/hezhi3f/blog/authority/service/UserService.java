package com.hezhi3f.blog.authority.service;

import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.entity.user.UserPO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient("BLOG-USER-SERVICE")
public interface UserService {
    @RequestMapping("/user/get")
    Result<UserPO> get(Long id);
}
