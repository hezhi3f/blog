package com.hezhi3f.bloguser.aspect;

import com.hezhi3f.bloguser.validate.validator.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Aspect
@Component
public class ValidateAspect {
    private final List<Validator> validators;

    @Pointcut("execution(public * com.hezhi3f.bloguser.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws IllegalAccessException {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            validateObject(arg);
        }
    }

    private void validateObject(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            for (Validator validator : validators) {
                validator.validate(obj, field)
                        .ifPresent(e -> {
                            throw e;
                        });
            }
        }
    }

    @Autowired
    public ValidateAspect(List<Validator> validators) {
        this.validators = validators;
    }
}
