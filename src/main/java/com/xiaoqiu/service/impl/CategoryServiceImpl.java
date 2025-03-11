package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.entity.Article;
import com.xiaoqiu.entity.Category;
import com.xiaoqiu.mapper.CategoryMapper;
import com.xiaoqiu.service.CategoryService;
import com.xiaoqiu.util.CacheService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final CacheService cacheService;

    public CategoryServiceImpl(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public List<Category> list() {
        String cacheKey = "categories:list";
        Object cachedCategories = cacheService.get(cacheKey,Category.class);
        if (cachedCategories != null) {
            return (List<Category>) cachedCategories;
        }

        List<Category> categories = super.list();
        cacheService.set(cacheKey, categories.toString(), 86400); // 缓存一天
        return categories;
    }

    @Override
    public boolean save(Category category) {
        if (super.save(category)) {
            cacheService.delete("categories:list");
            return true;
        }
        return false;
    }

    @Override
    public boolean updateById(Category category) {
        boolean success = super.updateById(category);
        if (success) {
            cacheService.delete("category:" + category.getId());
            cacheService.delete("category:list");
        }
        return success;
    }

    @Override
    public boolean removeById(Long id) {
        boolean success = super.removeById(id);
        if (success) {
            cacheService.delete("category:" + id);
            cacheService.delete("category:list");
        }
        return success;
    }

    @Override
    public Category getById(Long id) {
        String cacheKey = "article:" + id;
        Object cachedArticle = cacheService.get(cacheKey,Category.class);
        if (cachedArticle != null) {
            return (Category) cachedArticle;
        }

        Category category = super.getById(id);
        if (category != null) {
            cacheService.set(cacheKey, category.toString(), 1800); // 缓存30分钟
        }
        return category;
    }
}
