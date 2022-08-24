package com.hezhi3f.bloguser.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hezhi3f.bloguser.entity.user.UserPO;

public class TokenUtils {
    public static String create(UserPO userPO) {
        return JWT.create()
                .withClaim("id", userPO.getId())
                .withClaim("email", userPO.getEmail())
                .sign(Algorithm.HMAC256(userPO.getSecret()));
    }

    public static void verifyToken(String token, String secret) {
        JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        // if error throw
    }
}
