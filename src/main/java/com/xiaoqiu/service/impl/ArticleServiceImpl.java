package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.Article;
import com.xiaoqiu.service.ArticleService;
import com.xiaoqiu.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 25353
* @description 针对表【article(文章表)】的数据库操作Service实现
* @createDate 2025-03-11 19:52:13
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Override
    public List<Article> selectAllArticles() {
        //返回文章列表
        return list();
    }
    
    @Override
    public PageResult<Article> selectArticlesByPage(long current, long size) {
        // 创建分页对象
        Page<Article> page = new Page<>(current, size);
        // 执行分页查询
        Page<Article> articlePage = page(page);
        // 将MyBatis-Plus分页结果转换为自定义分页结果
        return PageResult.fromPage(articlePage);
    }

    @Override
    public Article selectArticleById(Long id) {
        return getById(id);
    }

    @Override
    public void insertArticle(Article article) {
        save(article);
    }

    @Override
    public void updateArticle(Article article) {
        updateById(article);
    }

    @Override
    public void deleteArticleById(Long id) {
        removeById(id);
    }
}




