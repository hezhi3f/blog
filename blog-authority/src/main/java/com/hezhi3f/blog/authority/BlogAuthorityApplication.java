package com.hezhi3f.blog.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BlogAuthorityApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAuthorityApplication.class, args);
    }

}
