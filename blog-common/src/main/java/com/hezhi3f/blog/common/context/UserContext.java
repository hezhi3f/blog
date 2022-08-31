package com.hezhi3f.blog.common.context;

import com.hezhi3f.blog.common.entity.user.UserPO;

public class UserContext {
    private static final ThreadLocal<UserPO> CONTEXT = new ThreadLocal<>();

    public static void set(UserPO userPO) {
        CONTEXT.set(userPO);
    }

    public static UserPO get() {
        return CONTEXT.get();
    }

}
