package com.hezhi3f.blog.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hezhi3f.blog.common.entity.authority.AuthorityPO;
import com.hezhi3f.blog.common.entity.user.UserPO;

import java.util.Calendar;

public class TokenUtils {
    public static String create(UserPO po) {
        return create(po, Calendar.HOUR, 1);
    }

    public static String create(UserPO po, int yield, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(yield, calendar.get(yield) + value);

        return JWT.create()
                .withClaim("id", po.getId())
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(po.getSecret()));
    }

    public static void verify(String token, String secret) {
        JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }

    public static void verify(DecodedJWT jwt, String secret) {
        JWT.require(Algorithm.HMAC256(secret)).build().verify(jwt);
    }
}
