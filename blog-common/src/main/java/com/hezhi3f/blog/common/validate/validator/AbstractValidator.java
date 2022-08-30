package com.hezhi3f.blog.common.validate.validator;

import com.hezhi3f.blog.common.validate.annotation.Required;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class AbstractValidator<T extends Annotation> implements Validator {

    @Override
    public final String validate(Object obj, Field field) throws IllegalAccessException {
        T t = field.getAnnotation(getAnnotationClass());
        if (t != null) {
            field.setAccessible(true);
            Object o = field.get(obj);
            if (!(t instanceof Required)) {
                if (o == null) {
                    return null;
                }
            }
            return judge(o, t);
        }

        return null;
    }

    protected abstract String judge(Object o, T t);

    protected abstract Class<T> getAnnotationClass();
}
