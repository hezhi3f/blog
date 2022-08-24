package com.hezhi3f.bloguser.validate.validator;

import com.hezhi3f.bloguser.exception.BlogUserException;
import com.hezhi3f.bloguser.validate.annotation.Length;
import org.springframework.stereotype.Component;

@Component
public class LengthValidator extends AbstractValidator<Length> {
    @Override
    protected RuntimeException judge(Object o, Length length) {
        int min = length.min();
        int max = length.max();
        String msg = length.msg();
        if (o instanceof String) {
            int len = ((String) o).length();

            if (min > len || max < len) {
                throw new BlogUserException(msg);
            }
        }

        return null;
    }

    @Override
    protected Class<Length> getAnnotationClass() {
        return Length.class;
    }
}
