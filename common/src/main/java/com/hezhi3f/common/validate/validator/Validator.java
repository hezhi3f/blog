package com.hezhi3f.common.validate.validator;

import java.lang.reflect.Field;

public interface Validator {
    String validate(Object obj, Field field) throws IllegalAccessException;
}
