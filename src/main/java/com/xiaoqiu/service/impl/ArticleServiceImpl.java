package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.entity.Article;
import com.xiaoqiu.entity.dto.ArticleDocument;
import com.xiaoqiu.mapper.ArticleMapper;
import com.xiaoqiu.mq.MessageProducer;
import com.xiaoqiu.service.ArticleService;
import com.xiaoqiu.util.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final ESService esService;
    private final CacheService cacheService;
    private final MessageProducer messageProducer;

    public ArticleServiceImpl(ESService esService, CacheService cacheService, MessageProducer messageProducer) {
        this.esService = esService;
        this.cacheService = cacheService;
        this.messageProducer = messageProducer;
    }

    @Override
    public boolean save(Article article) {
        boolean result = super.save(article);

        if (result) {
            // 同步到 Elasticsearch
            syncToES(article);

            // 清除 Redis 缓存
            cacheService.delete("article:list");

            // 发送日志消息到 MQ
            messageProducer.sendLog("📝 发布文章成功：" + article.getTitle());
        }
        return result;
    }

    @Override
    public boolean updateById(Article article) {
        boolean result = super.updateById(article);

        if (result) {
            // 同步到 Elasticsearch
            syncToES(article);

            // 清除 Redis 缓存
            cacheService.delete("article:list");
            cacheService.delete("article:" + article.getId());

            // 发送日志消息到 MQ
            messageProducer.sendLog("✏️ 更新文章成功：" + article.getTitle());
        }
        return result;
    }

    @Override
    public boolean removeById(Long id) {
        Article article = getById(id);
        boolean result = super.removeById(id);

        if (result) {
            // 删除 Elasticsearch 文档
            deleteFromES(id);

            // 清除 Redis 缓存
            cacheService.delete("article:list");
            cacheService.delete("article:" + id);

            // 发送日志消息到 MQ
            messageProducer.sendLog("❌ 删除文章成功：" + (article != null ? article.getTitle() : "ID=" + id));
        }
        return result;
    }

    @Override
    public Article getArticleDetail(Long id) {
        String cacheKey = "article:" + id;
        Article article = cacheService.get(cacheKey, Article.class);

        if (article != null) {
            return article;
        }

        article = getById(id);
        if (article != null) {
            cacheService.set(cacheKey, article);
        }
        return article;
    }

    @Override
    public List<Article> listArticles() {
        String cacheKey = "article:list";
        List<Article> list = cacheService.getList(cacheKey, Article.class);

        if (list != null && !list.isEmpty()) {
            return list;
        }

        list = list(); // 查询数据库
        cacheService.set(cacheKey, list);
        return list;
    }

    private void syncToES(Article article) {
        try {
            ArticleDocument doc = new ArticleDocument();
            BeanUtils.copyProperties(article, doc);
            esService.saveArticle(doc);
        } catch (IOException e) {
            log.error("同步文章到 Elasticsearch 失败", e);
        }
    }

    private void deleteFromES(Long id) {
        try {
            esService.deleteArticle(id);
        } catch (IOException e) {
            log.error("删除 Elasticsearch 文档失败", e);
        }
    }
}
