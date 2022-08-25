package com.hezhi3f.bloguser.aspect;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.hezhi3f.bloguser.entity.result.Result;
import com.hezhi3f.bloguser.exception.BlogUserException;
import com.hezhi3f.bloguser.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAspect {
    private static final Integer TOKEN_ERROR_CODE = 500;


    @ExceptionHandler({BlogUserException.class})
    public Result<Void> handler(BlogUserException e) {
        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        } else {
            log.warn("请求接口异常[{}]，因为{}", e.getCode(), e.getMessage());
        }
        return ResultUtils.error(e.getMessage());
    }

    @ExceptionHandler({SignatureVerificationException.class})
    public Result<Void> handler(SignatureVerificationException e) {
        String msg = "token验证失败";
        log.info(msg + ":" + e.getMessage());
        return ResultUtils.error(TOKEN_ERROR_CODE, msg);
    }

    @ExceptionHandler({TokenExpiredException.class})
    public Result<Void> handler(TokenExpiredException e) {
        String msg = "token已过期";
        log.info(msg + ":" + e.getMessage());
        return ResultUtils.error(TOKEN_ERROR_CODE, msg);
    }
}
