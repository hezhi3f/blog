package com.hezhi3f.bloguser.exception;

public class BlogUserException extends RuntimeException {
    private Integer code = 0;

    public BlogUserException(String message) {
        super(message);
    }

    private BlogUserException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
