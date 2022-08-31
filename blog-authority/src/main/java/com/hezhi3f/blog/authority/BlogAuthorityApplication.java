package com.hezhi3f.blog.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScans({
        @ComponentScan("com.hezhi3f.blog.common")
})
public class BlogAuthorityApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAuthorityApplication.class, args);
    }

}