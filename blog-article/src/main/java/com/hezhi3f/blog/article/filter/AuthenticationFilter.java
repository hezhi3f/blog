package com.hezhi3f.blog.article.filter;


import com.alibaba.fastjson.JSON;
import com.hezhi3f.blog.article.api.AuthorityService;
import com.hezhi3f.blog.common.context.UserContext;
import com.hezhi3f.blog.common.entity.result.Result;
import com.hezhi3f.blog.common.entity.user.UserPO;
import org.jetbrains.annotations.NotNull;
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
    private static final Set<String> ALLOW_PATH = Set.of("/article/search", "/article/view");
    private final AuthorityService authorityService;

    @Autowired
    public AuthenticationFilter(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        // 某些接口不需要token，直接放行
        if (ALLOW_PATH.contains(path)) {
            filterChain.doFilter(request, response);
        }

        String token = request.getHeader("token");
        Result<UserPO> result = authorityService.verify(token);
        // 成功放行，失败返回错误信息
        if (result.getOk()) {
            UserContext.set(result.getData());
            filterChain.doFilter(request, response);
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(JSON.toJSONString(result));
        }
    }
}
