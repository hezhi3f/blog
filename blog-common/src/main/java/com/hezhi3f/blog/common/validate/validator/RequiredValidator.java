package com.hezhi3f.blog.common.validate.validator;

import com.hezhi3f.blog.common.validate.annotation.Required;
import org.springframework.stereotype.Component;

@Component
public class RequiredValidator extends AbstractValidator<Required> {
    @Override
    protected String judge(Object o, Required required) {
        if (o == null) {
            return required.msg();
        }
        return null;
    }

    @Override
    protected Class<Required> getAnnotationClass() {
        return Required.class;
    }
}
