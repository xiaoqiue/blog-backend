package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.Category;
import com.xiaoqiu.service.CategoryService;
import com.xiaoqiu.mapper.CategoryMapper;

import java.util.List;

import org.springframework.stereotype.Service;

/**
* @author 25353
* @description 针对表【category(分类表)】的数据库操作Service实现
* @createDate 2025-03-11 19:53:28
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Override
    public PageResult<Category> selectCategoriesByPage(long current, long size) {
        Page<Category> page = new Page<>(current, size);
        Page<Category> categoryPage = page(page);
        return PageResult.fromPage(categoryPage);
    }

    @Override
    public List<Category> selectAllCategories() {
        return list();
    }

    @Override
    public Category selectCategoryById(Long id) {
        return getById(id);
    }

    @Override
    public void insertCategory(Category category) {
         save(category); 
    }

    @Override
    public void updateCategory(Category category) {
        updateById(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        removeById(id);
    }

}




