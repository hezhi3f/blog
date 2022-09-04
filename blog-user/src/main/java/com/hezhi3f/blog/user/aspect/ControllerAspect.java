package com.hezhi3f.blog.user.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.FieldSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] names = signature.getParameterNames();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            sb.append(names[i]).append("=").append(args[i]).append(",");
        }

        log.info("请求{}，参数为{}", name, sb.deleteCharAt(sb.length() - 1));
    }

    @AfterReturning(returning = "result", pointcut = "pointCut()")
    public void after(Object result) {
        log.info("相应结果为:{}", result);
    }
}
