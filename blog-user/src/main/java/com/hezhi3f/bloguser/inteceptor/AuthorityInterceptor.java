package com.hezhi3f.bloguser.inteceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hezhi3f.bloguser.entity.user.UserPO;
import com.hezhi3f.bloguser.service.UserService;
import com.hezhi3f.bloguser.util.Assert;
import com.hezhi3f.bloguser.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {
    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Assert.notNull(token, "认证信息token不存在，权限不足");
        log.info("token: {}", token);
        DecodedJWT jwt = JWT.decode(token);
        Integer id = jwt.getClaim("id").asInt();
        String email = jwt.getClaim("email").asString();

        UserPO userPO = userService.getOne(Wrappers.<UserPO>query().eq("id", id).eq("email", email));

        Assert.notNull(userPO, "错误的token参数");
        Assert.isFalse(userPO.getDeleted(), "用户已经注销");
        TokenUtils.verify(token, userPO.getSecret());

        request.setAttribute("id", id);
        return true;
    }

    @Autowired
    public AuthorityInterceptor(UserService userService) {
        this.userService = userService;
    }
}
