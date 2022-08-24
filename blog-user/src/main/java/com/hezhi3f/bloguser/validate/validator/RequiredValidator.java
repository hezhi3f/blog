package com.hezhi3f.bloguser.validate.validator;

import com.hezhi3f.bloguser.exception.BlogUserException;
import com.hezhi3f.bloguser.validate.annotation.Required;
import org.springframework.stereotype.Component;

@Component
public class RequiredValidator extends AbstractValidator<Required> {
    @Override
    protected RuntimeException judge(Object o, Required required) {
        if (o == null) {
            return new BlogUserException(required.msg());
        }
        return null;
    }

    @Override
    protected Class<Required> getAnnotationClass() {
        return Required.class;
    }
}