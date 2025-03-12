package com.xiaoqiu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiaoqiu
 * @date 2025/3/11 19:28
 * @description
 */
@SpringBootApplication
@MapperScan("com.xiaoqiu.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
        System.out.println("启动成功");
    }

}
