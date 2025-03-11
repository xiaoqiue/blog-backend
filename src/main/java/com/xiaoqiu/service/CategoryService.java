package com.xiaoqiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqiu.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    @Override
    List<Category> list();
    @Override
    boolean save(Category category);
    @Override
    boolean updateById(Category category);
    boolean removeById(Long id);
    Category getById(Long id);

}
