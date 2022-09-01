package com.hezhi3f.blog.article.api;

import com.hezhi3f.blog.common.entity.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("BLOG-USER-SERVICE")
public interface UserService {
    @RequestMapping("/user/nickname")
    Result<String> getNickname(@RequestParam Long id);
}
