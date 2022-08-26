package com.hezhi3f.common.validate.validator;

import com.hezhi3f.common.validate.annotation.Range;
import org.springframework.stereotype.Component;

@Component
public class RangeValidator extends AbstractValidator<Range> {
    @Override
    protected String judge(Object o, Range range) {
        long l = Long.parseLong(o.toString());
        long min = range.min();
        long max = range.max();
        if (min > l || max < l) {
            return range.msg();
        }
        return null;
    }

    @Override
    protected Class<Range> getAnnotationClass() {
        return Range.class;
    }
}
