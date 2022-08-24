package com.hezhi3f.bloguser.test;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MyTest {

    @Test
    void test() {
        String[] demos = {"4353953",
                "234244",
                "5f34ff",
                "43tg34",
                "hezjio@foe.com",
                "fmoe@fe.cdmi好",
                "fn加mf@ocmd.cold",
                "GRgr@42.35fr@frfewf.com"};

        Pattern checkCode1 = Pattern.compile("^\\d{6}$");
        Pattern checkCode2 = Pattern.compile("\\d{6}");
        Pattern email1 = Pattern.compile("^[a-zA-Z\\d]+@[a-zA-Z\\d]+\\.[a-zA-Z\\d]+$");
        Pattern email2 = Pattern.compile("[a-zA-Z\\d]+@[a-zA-Z\\d]+\\.[a-zA-Z\\d]+");

        for (String demo : demos) {
            boolean matches = checkCode1.matcher(demo).matches();
            int i = checkCode1.matcher(demo).groupCount();
            if (matches) {
                System.out.println(checkCode1.pattern() + "::" + demo + "::" + i);
            }
        }

        for (String demo : demos) {
            boolean matches = checkCode2.matcher(demo).matches();
            if (matches) {
                System.out.println(checkCode2.pattern() + "::" + demo);
            }
        }
        for (String demo : demos) {
            boolean matches = email1.matcher(demo).matches();
            if (matches) {
                System.out.println(email1.pattern() + "::" + demo);
            }
        }
        for (String demo : demos) {
            boolean matches = email2.matcher(demo).matches();
            if (matches) {
                System.out.println(email2.pattern() + "::" + demo);
            }
        }

    }

}
