package com.xiaoqiu.entity.dto;

import lombok.Data;

@Data
public class ArticleDocument {
    private Long id;
    private String title;
    private String content;
    private String description;
    private String tags;
}
