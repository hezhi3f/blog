package com.hezhi3f.common.aspect;

import com.hezhi3f.common.util.Assert;
import com.hezhi3f.common.validate.annotation.Validated;
import com.hezhi3f.common.validate.validator.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.List;

@Aspect
@Component
public class ValidateAspect {
    private final List<Validator> validators;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws IllegalAccessException {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Validated validated = parameters[i].getAnnotation(Validated.class);
            if (validated != null) {
                validateObject(args[i]);
            }
        }
    }


    private void validateObject(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            for (Validator validator : validators) {
                String msg = validator.validate(obj, field);
                Assert.isNull(msg, msg);
            }
        }
    }

    @Autowired
    public ValidateAspect(List<Validator> validators) {
        this.validators = validators;
    }
}
