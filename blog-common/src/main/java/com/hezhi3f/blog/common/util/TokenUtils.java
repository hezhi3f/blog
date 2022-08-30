package com.hezhi3f.blog.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hezhi3f.blog.common.entity.user.UserPO;

import java.util.Calendar;

public class TokenUtils {
    public static String create(UserPO userPO) {
        return create(userPO, Calendar.HOUR, 1);
    }

    public static String create(UserPO userPO, int yield, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(yield, calendar.get(yield) + value);

        return JWT.create()
                .withClaim("id", userPO.getId())
                .withClaim("email", userPO.getEmail())
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(userPO.getSecret()));
    }

    public static void verify(String token, String secret) {
        JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        // if error throw
    }

    public static Long getAsLong(String token, String claim) {
        return JWT.decode(token).getClaim(claim).asLong();
    }
}
