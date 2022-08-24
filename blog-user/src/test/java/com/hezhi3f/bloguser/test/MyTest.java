package com.hezhi3f.bloguser.test;

import com.hezhi3f.bloguser.validate.annotation.RegexEnum;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MyTest {

    @Test
    void test() {
        String[] demos = {
                "4353953",
                "234244",
                "5f34ff",
                "43tg34",
                "hezjio@foe.com",
                "fmoe@fe.cdmi好",
                "fn加mf@ocmd.cold",
                "GRgr@42.35fr@frfewf.com"
        };

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

    @Test
    void pass() {
        String[] passes = {
                "nefni024nfif",
                "f43o",
                "mfiom30iFJ39J.$%@#3vfNONN9",
                "43@#$r342FK0f$#J98F2",
                "9043F]FDK0",
                "FK04V 4JF0 2 D2"
        };

        Pattern pa = Pattern.compile(RegexEnum.PASSWORD);
        for (String pass : passes) {
            if (pa.matcher(pass).matches()) {
                System.out.println("match :: " + pass);
            }
        }

    }

    @Test
    void cast() {
        double x = 48943.9504;
        long a = (long) x;
        System.out.println(a);
    }

}
