package com.hezhi3f.blogarticle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(
        @ComponentScan("com.hezhi3f.common")
)
public class BlogArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogArticleApplication.class, args);
    }

}
