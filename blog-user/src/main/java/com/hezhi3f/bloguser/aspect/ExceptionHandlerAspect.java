package com.hezhi3f.bloguser.aspect;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hezhi3f.common.entity.result.Result;
import com.hezhi3f.common.exception.BlogException;
import com.hezhi3f.common.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAspect {
    private static final Integer TOKEN_ERROR_CODE = 500;


    @ExceptionHandler({BlogException.class})
    public Result<Void> handler(BlogException e) {
        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        } else {
            log.warn("请求接口异常[{}]，因为{}", e.getCode(), e.getMessage());
        }
        return ResultUtils.error(e.getMessage());
    }

    @ExceptionHandler({JWTVerificationException.class})
    public Result<Void> handler(JWTVerificationException e) {
        String msg = "认证信息出错，或者认证信息已过期！";

        log.info(msg + ":" + e.getMessage());
        return ResultUtils.error(TOKEN_ERROR_CODE, msg);
    }
}
