package com.hezhi3f.bloguser.validate.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Optional;

public abstract class AbstractValidator<T extends Annotation> implements Validator {

    @Override
    public final Optional<RuntimeException> validate(Object obj, Field field) throws IllegalAccessException {
        T t = field.getAnnotation(getAnnotationClass());
        RuntimeException e = null;
        if (t != null) {
            field.setAccessible(true);
            Object o = field.get(obj);
            e = judge(o, t);
        }

        return Optional.ofNullable(e);
    }

    protected abstract RuntimeException judge(Object o, T t);
    protected abstract Class<T> getAnnotationClass();
}
