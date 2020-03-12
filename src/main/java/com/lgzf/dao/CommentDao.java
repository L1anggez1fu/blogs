package com.lgzf.dao;

import com.lgzf.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther lgzf
 * @data 2020-03-05 - 15:21
 **/

@Repository
@Mapper
public interface CommentDao {

    //根据创建时间倒序来排
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    //查询父级对象
    Comment findByParentCommentId(Long parentCommentId);

    //添加一个评论
    int saveComment(Comment comment);

    Comment getParentCommentById(Long id);

}
