package com.hezhi3f.bloguser.validate.validator;

import java.lang.reflect.Field;
import java.util.Optional;

public interface Validator {
    String validate(Object obj, Field field) throws IllegalAccessException;
}
