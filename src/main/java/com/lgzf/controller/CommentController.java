package com.lgzf.controller;

import com.lgzf.pojo.Comment;
import com.lgzf.pojo.User;
import com.lgzf.service.BlogService;
import com.lgzf.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @auther lgzf
 * @data 2020-03-05 - 15:15
 **/
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;
    /*根据blogId获得博客下的评论*/
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);

        for (int i = 0; i < comments.size(); i++) {
            System.out.println(comments.get(i));
            System.out.println(comments.get(i).getReplyComments());
        }


        model.addAttribute("comments", comments);
        model.addAttribute("blog", blogService.getDetailedBlog(blogId));
        /*将数据放回给blog页面的commentList模块*/
        return "blog :: commentList";
    }

    /*提交评论*/
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlogId();
        //set Blog
        comment.setBlog(blogService.getDetailedBlog(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
        } else {
            //设置头像
            comment.setAvatar(avatar);
        }
        if (comment.getParentComment().getId() != null) {
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        commentService.saveComment(comment);
        return "redirect:/blog/" + blogId;
    }
}
