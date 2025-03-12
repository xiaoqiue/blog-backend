package com.xiaoqiu.service;

import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.Category;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 25353
* @description 针对表【category(分类表)】的数据库操作Service
* @createDate 2025-03-11 19:53:28
*/
public interface CategoryService extends IService<Category> {

    PageResult<Category> selectCategoriesByPage(long current, long size);
    List<Category> selectAllCategories();

    Category selectCategoryById(Long id);

    void insertCategory(Category category);

    void updateCategory(Category category);

    void deleteCategoryById(Long id);

}
