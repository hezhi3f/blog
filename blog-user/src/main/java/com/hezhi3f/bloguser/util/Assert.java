package com.hezhi3f.bloguser.util;

import com.hezhi3f.bloguser.exception.BlogUserException;

import java.util.Objects;

public class Assert {
    public static void isEquals(Object o1, Object o2, String msg) {
        if (!Objects.equals(o1, o2)) {
            throw new BlogUserException(msg);
        }
    }

    public static void isTrue(boolean b, String msg) {
        if (!b) {
            throw new BlogUserException(msg);
        }
    }

    public static void isNotNull(Object o, String msg) {
        if (o == null) {
            throw new BlogUserException(msg);
        }

    }

    public static void isNull(Object o, String msg) {
        if (o != null) {
            throw new BlogUserException(msg);
        }
    }
}
