package com.hezhi3f.blog.common.validate.validator;

import com.hezhi3f.blog.common.validate.annotation.Type;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TypeValidator extends AbstractValidator<Type> {
    private static final Map<String, Pattern> REGEXES = new HashMap<>();

    @Override
    protected String judge(Object o, Type type) {
        String regex = type.regex();

        // 给正则加缓存
        if (!REGEXES.containsKey(regex)) {
            REGEXES.put(regex, Pattern.compile(regex));
        }

        Pattern pattern = REGEXES.get(regex);

        if (o instanceof String) {
            Matcher matcher = pattern.matcher((String) o);
            if (!matcher.matches()) {
                return type.msg();
            }
        }

        return null;
    }

    @Override
    protected Class<Type> getAnnotationClass() {
        return Type.class;
    }
}
