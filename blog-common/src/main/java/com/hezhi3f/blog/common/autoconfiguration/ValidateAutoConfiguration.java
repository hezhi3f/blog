package com.hezhi3f.blog.common.autoconfiguration;

import com.hezhi3f.blog.common.aspect.ValidateAspect;
import com.hezhi3f.blog.common.validate.validator.Validator;
import com.hezhi3f.blog.common.validate.validator.impl.LengthValidator;
import com.hezhi3f.blog.common.validate.validator.impl.RangeValidator;
import com.hezhi3f.blog.common.validate.validator.impl.RequiredValidator;
import com.hezhi3f.blog.common.validate.validator.impl.TypeValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@ConditionalOnProperty(
        prefix = ValidateAutoConfiguration.PREFIX,
        value = "enable",
        matchIfMissing = true)
public class ValidateAutoConfiguration {
    public static final String PREFIX = "blog.common.validated";

    @Bean
    public Validator requiredValidator() {
        return new RequiredValidator();
    }

    @Bean
    public Validator lengthValidator() {
        return new LengthValidator();
    }

    @Bean
    public Validator rangeValidator() {
        return new RangeValidator();
    }

    @Bean
    public Validator typeValidator() {
        return new TypeValidator();
    }

    @Bean
    public ValidateAspect validateAspect() {
        List<Validator> list = Arrays.asList(
                requiredValidator(),
                lengthValidator(),
                rangeValidator(),
                typeValidator()
        );

        return new ValidateAspect(list);
    }
}
