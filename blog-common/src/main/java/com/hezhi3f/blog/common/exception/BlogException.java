package com.hezhi3f.blog.common.exception;

public class BlogException extends RuntimeException{
    private Integer code = 0;

    public BlogException(String message) {
        super(message);
    }

    private BlogException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
