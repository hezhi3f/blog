package com.hezhi3f.bloguser.util;

import com.hezhi3f.bloguser.entity.result.Result;

public class ResultUtils {
    public static <T> Result<T> success() {
        Result<T> r = new Result<>();
        r.setCode(200);
        r.setOk(true);
        r.setMsg("success");
        return r;
    }

    public static <T> Result<T> success(T t) {
        Result<T> r = success();
        r.setData(t);
        return r;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> r = new Result<>();
        r.setCode(1000);
        r.setOk(false);
        r.setMsg(msg);
        return r;
    }
}

