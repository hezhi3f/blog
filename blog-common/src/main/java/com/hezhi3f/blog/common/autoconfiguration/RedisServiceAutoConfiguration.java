package com.hezhi3f.blog.common.autoconfiguration;


import com.hezhi3f.blog.common.service.RedisService;
import com.hezhi3f.blog.common.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@ConditionalOnProperty(
        prefix = RedisServiceAutoConfiguration.PREFIX,
        value = "enabled",
        matchIfMissing = true)
public class RedisServiceAutoConfiguration {
    public static final String PREFIX = "blog.common.redis-service";

    private final StringRedisTemplate stringRedisTemplate;

    @Bean
    public RedisService redisService() {
        return new RedisServiceImpl(stringRedisTemplate);
    }

    @Autowired
    public RedisServiceAutoConfiguration(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
}
