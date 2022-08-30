package com.hezhi3f.blog.common.validate.validator;

import com.hezhi3f.blog.common.validate.annotation.Length;
import org.springframework.stereotype.Component;

@Component
public class LengthValidator extends AbstractValidator<Length> {
    @Override
    protected String judge(Object o, Length length) {
        int min = length.min();
        int max = length.max();
        if (o instanceof String) {
            int len = ((String) o).length();

            if (min > len || max < len) {
                return length.msg();
            }
        }

        return null;
    }

    @Override
    protected Class<Length> getAnnotationClass() {
        return Length.class;
    }
}
