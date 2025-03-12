package com.xiaoqiu.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章标签关联表
 * @TableName article_tag
 */
@TableName(value ="article_tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTag implements Serializable {
    /**
     * 文章ID
     */

    private Long articleId;

    /**
     * 标签ID
     */

    private Long tagId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}