package com.xiaoqiu.service.impl;

import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;

    @Override
    public String uploadFile(MultipartFile file, String bucketName) {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        try {
            // 检查桶是否存在，如果不存在则创建
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            // 上传文件
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());

            // 生成预签名URL
            String presignedUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET) // 指定HTTP方法为GET
                            .bucket(bucketName)
                            .object(fileName)
                            .expiry(60 * 60 * 24) // 设置URL有效期，单位为秒（例如1天）
                            .build());

            return presignedUrl;
        } catch (Exception e) {
            log.error("上传失败", e);
            return null;
        }
    }
}
