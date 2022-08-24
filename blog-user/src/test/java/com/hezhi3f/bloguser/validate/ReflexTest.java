package com.hezhi3f.bloguser.validate;

import com.hezhi3f.bloguser.entity.user.UserLoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@SpringBootTest
public class ReflexTest {

    @Test
    void test() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("2657520652@qq.com");

        Field[] fields = userLoginDTO.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }

        }
    }
}
