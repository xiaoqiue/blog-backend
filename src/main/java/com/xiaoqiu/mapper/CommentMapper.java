package com.xiaoqiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoqiu.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
