package com.xiaoqiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqiu.entity.ArticleTag;
import com.xiaoqiu.service.ArticleTagService;
import com.xiaoqiu.mapper.ArticleTagMapper;
import org.springframework.stereotype.Service;

/**
* @author 25353
* @description 针对表【article_tag(文章标签关联表)】的数据库操作Service实现
* @createDate 2025-03-11 19:53:28
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService{

}




