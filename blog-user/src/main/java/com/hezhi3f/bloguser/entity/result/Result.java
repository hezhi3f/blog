package com.hezhi3f.bloguser.entity.result;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private Boolean ok;
    private String msg;
    private T data;
}
