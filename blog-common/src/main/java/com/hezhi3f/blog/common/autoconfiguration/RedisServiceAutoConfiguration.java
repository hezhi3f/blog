package com.hezhi3f.blog.common.autoconfiguration;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
        prefix = RedisServiceAutoConfiguration.PREFIX,
        value = "enabled",
        matchIfMissing = true)
public class RedisServiceAutoConfiguration {
    public static final String PREFIX = "blog.common.redis-service";
}
