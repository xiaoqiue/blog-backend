package com.xiaoqiu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章表
 * @TableName article
 */
@Schema(description = "文章实体类")
@TableName(value ="article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    /**
     * 文章ID
     */
    @Schema(description = "文章ID", example = "1")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题
     */
    @Schema(description = "文章标题", example = "Spring Boot 入门教程")
    private String title;

    /**
     * 文章内容
     */
    @Schema(description = "文章内容", example = "这是文章的详细内容...")
    private String content;

    /**
     * 文章摘要
     */
    @Schema(description = "文章摘要", example = "这是一篇关于Spring Boot的入门教程...")
    private String summary;

    /**
     * 封面图片URL
     */
    @Schema(description = "封面图片URL", example = "http://example.com/images/cover.jpg")
    private String coverImage;

    /**
     * 分类ID
     */
    @Schema(description = "分类ID", example = "1")
    private Long categoryId;

    /**
     * 作者ID
     */
    @Schema(description = "作者ID", example = "1")
    private Long userId;

    /**
     * 状态：0-草稿，1-待审核，2-已发布，3-已拒绝
     */
    @Schema(description = "状态：0-草稿，1-待审核，2-已发布，3-已拒绝", example = "2")
    private Integer status;

    /**
     * 浏览量
     */
    @Schema(description = "浏览量", example = "100")
    private Integer viewCount;

    /**
     * 点赞数
     */
    @Schema(description = "点赞数", example = "50")
    private Integer likeCount;

    /**
     * 评论数
     */
    @Schema(description = "评论数", example = "30")
    private Integer commentCount;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "2023-01-01 12:00:00")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间", example = "2023-01-02 12:00:00")
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}