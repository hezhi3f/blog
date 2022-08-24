package com.hezhi3f.bloguser.util;

import java.util.Random;
import java.util.UUID;

public class CodeUtils {
    private static final char[] NUMS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z'
    };
    private static final Random r = new Random();

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static String num6() {
        char[] num6 = new char[6];
        for (int i = 0; i < num6.length; i++) {
            num6[i] = randomOne(NUMS);
        }

        return String.valueOf(num6);
    }

    public static String char6() {
        char[] char6 = new char[6];
        for (int i = 0; i < char6.length; i++) {
            char6[i] = randomOne(CHARS);
        }
        return String.valueOf(char6);
    }

    private static char randomOne(char[] chars) {
        return chars[r.nextInt(chars.length)];
    }
}
