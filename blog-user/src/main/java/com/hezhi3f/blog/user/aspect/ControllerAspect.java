package com.hezhi3f.blog.user.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Pointcut("execution(* com.hezhi3f.blog.user.controller.*.*(..))")
    private void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("请求{}，参数为{}", name, args);
    }

    @AfterReturning(returning = "result", pointcut = "pointCut()")
    public void after(Object result) {
        log.info("相应结果为:{}", result);
    }
}
