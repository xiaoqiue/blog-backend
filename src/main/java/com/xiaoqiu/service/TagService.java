package com.xiaoqiu.service;

import com.xiaoqiu.common.PageResult;
import com.xiaoqiu.entity.Tag;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 25353
* @description 针对表【tag(标签表)】的数据库操作Service
* @createDate 2025-03-11 19:53:28
*/
public interface TagService extends IService<Tag> {

    PageResult<Tag> selectTagsByPage(long current, long size);
    List<Tag> selectAllTags();

    Tag selectTagById(Long id);

    void insertTag(Tag tag);

    void deleteTagById(Long id);

    void updateTag(Tag tag);

}
