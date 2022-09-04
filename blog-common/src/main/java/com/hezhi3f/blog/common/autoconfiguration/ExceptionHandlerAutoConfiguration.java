package com.hezhi3f.blog.common.autoconfiguration;

import com.hezhi3f.blog.common.aspect.ExceptionHandlerAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
        prefix = ExceptionHandlerAutoConfiguration.PREFIX,
        value = "enabled",
        matchIfMissing = true)
public class ExceptionHandlerAutoConfiguration {
    public static final String PREFIX = "blog.common.exception-handler";

    @ConditionalOnMissingBean
    @Bean
    public ExceptionHandlerAspect exceptionHandlerAspect() {
        return new ExceptionHandlerAspect();
    }
}
