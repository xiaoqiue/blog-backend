package com.xiaoqiu.service;

import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 25353
* @description 针对表【article(文章表)】的数据库操作Service
* @createDate 2025-03-11 19:52:13
*/
public interface ArticleService extends IService<Article> {

    List<Article> selectAllArticles();
    
    /**
     * 分页查询文章列表
     * @param current 当前页码
     * @param size 每页大小
     * @return 分页结果
     */
    PageResult<Article> selectArticlesByPage(long current, long size);

    Article selectArticleById(Long id);

    void insertArticle(Article article);

    void updateArticle(Article article);

    void deleteArticleById(Long id);
}
