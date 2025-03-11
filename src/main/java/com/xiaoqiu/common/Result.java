package com.xiaoqiu.common;

import com.xiaoqiu.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(200, msg, null);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }

    public static Result<?> ok(Map<String, String> accessToken) {
        return new Result<>(200, "登录成功", accessToken);
    }

    public static Result<?> ok(List<Category> list) {
        return new Result<>(200, "查询成功", list);
    }

    public static Result<?> ok() {
        return new Result<>(200, "操作成功", null);
    }
}
