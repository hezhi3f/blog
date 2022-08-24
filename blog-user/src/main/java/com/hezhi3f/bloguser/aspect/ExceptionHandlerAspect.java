package com.hezhi3f.bloguser.aspect;

import com.hezhi3f.bloguser.entity.result.Result;
import com.hezhi3f.bloguser.exception.BlogUserException;
import com.hezhi3f.bloguser.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAspect {


    @ExceptionHandler({BlogUserException.class})
    public Result<Void> handler(BlogUserException e) {
        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        } else {
            log.warn(e.getMessage());
        }
        return ResultUtils.error(e.getMessage());
    }
}
