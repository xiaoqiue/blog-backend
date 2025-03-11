package com.xiaoqiu.controller;

import com.xiaoqiu.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {

    private final MinioService minioService;

    @PostMapping("/image")
    public Result<?> upload(@RequestParam("file") MultipartFile file) {
        String url = minioService.uploadFile(file, "blog");
        return Result.ok(Map.of("url", url));
    }
}
