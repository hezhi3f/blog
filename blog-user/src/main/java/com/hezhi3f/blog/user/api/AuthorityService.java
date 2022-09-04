package com.hezhi3f.blog.user.api;

import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.entity.user.UserPO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient("BLOG-AUTHORITY-SERVICE")
public interface AuthorityService {
    @RequestMapping("/authority/verify")
    Result<UserPO> verify(@RequestBody String token);

    @RequestMapping("/authority/refresh")
    Result<String> refresh(@RequestBody UserPO userPO);
}
