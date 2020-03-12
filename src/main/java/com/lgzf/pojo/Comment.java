package com.lgzf.pojo;

import com.lgzf.dto.DetailedBlog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-26 - 14:58
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;

    //头像
    private String avatar;
    private Date createTime;

    private Long blogId;
    private Long parentCommentId;
    private String parentNickname;

    //回复评论
    private List<Comment> replyComments = new ArrayList<>();

    private Comment parentComment;

    private DetailedBlog blog;

    private boolean adminComment;

}
