package com.hezhi3f.blog.user.filter;


import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.user.api.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
@Order
public class AuthenticationFilter extends OncePerRequestFilter {
    private static final Set<String> ALLOW_PATH = Set.of("/user/login", "/user/signup");
    private final AuthorityService authorityService;

    @Autowired
    public AuthenticationFilter(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        // 某些接口不需要token，直接放行
        if (ALLOW_PATH.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("token");
        Result<Void> result = authorityService.verify(token);
        // 成功放行，失败返回错误信息
        if (result.getOk()) {
            filterChain.doFilter(request, response);
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(result.toString());
        }
    }
}
