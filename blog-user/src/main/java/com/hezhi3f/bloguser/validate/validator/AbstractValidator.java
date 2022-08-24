package com.hezhi3f.bloguser.validate.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Optional;

public abstract class AbstractValidator<T extends Annotation> implements Validator {

    @Override
    public final String validate(Object obj, Field field) throws IllegalAccessException {
        T t = field.getAnnotation(getAnnotationClass());
        String msg = null;
        if (t != null) {
            field.setAccessible(true);
            Object o = field.get(obj);
            msg = judge(o, t);
        }

        return msg;
    }

    protected abstract String judge(Object o, T t);
    protected abstract Class<T> getAnnotationClass();
}
