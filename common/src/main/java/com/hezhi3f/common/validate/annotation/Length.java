package com.hezhi3f.common.validate.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Length {
    int min() default 0;

    int max() default Integer.MAX_VALUE;

    String msg() default "参数长度不正确";
}
