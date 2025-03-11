package com.xiaoqiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqiu.entity.Article;

import java.util.List;

public interface ArticleService extends IService<Article> {

    boolean removeById(Long id);

    Article getArticleDetail(Long id);

    List<Article> listArticles();
}
