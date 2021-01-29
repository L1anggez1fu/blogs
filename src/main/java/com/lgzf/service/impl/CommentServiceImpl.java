package com.lgzf.service.impl;

import com.lgzf.dao.BlogDao;
import com.lgzf.dao.CommentDao;
import com.lgzf.pojo.Comment;
import com.lgzf.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther lgzf
 * @data 2020-03-05 - 15:22
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //没有父节点的默认为-1
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        return eachComment(comments);
    }

    @Override
    public Comment getParentCommentById(Long id) {
        return commentDao.getParentCommentById(id);
    }

    @Override
    public int saveComment(Comment comment) {
        /*判断是否有上级评论*/
        Long parentCommentId = comment.getParentCommentId();
        if (parentCommentId != -1) {
            //有上级评论
            comment.setParentComment(commentDao.findByParentCommentId(comment.getParentCommentId()));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        comment.setBlog(blogDao.getDetailedBlog(comment.getBlogId()));
        return commentDao.saveComment(comment);
    }

    /**
     * 循环每个顶级的评论节点
     *
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     * 查询是否有子评论
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for (Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * 递归迭代，找到子集回复
     *
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replys = comment.getReplyComments();
            /*循环找出子评论的子评论*/
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }
}

