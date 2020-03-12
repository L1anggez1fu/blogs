package com.lgzf.service;

import com.lgzf.pojo.Comment;

import java.util.List;

/**
 * @auther lgzf
 * @data 2020-03-05 - 15:20
 **/

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    int saveComment(Comment comment);

    Comment getParentCommentById(Long id);
}
