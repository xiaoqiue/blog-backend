package com.xiaoqiu.common;

import java.io.Serializable;
import lombok.Data;

@Data
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    // 构造器私有，外部通过静态方法调用
    private Result() {
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功返回（无数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    // 成功返回（有数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 成功返回（自定义消息+数据）
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(200, msg, data);
    }

    // 失败返回（自定义消息）
    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }

    // 失败返回（自定义状态码+消息）
    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }
}