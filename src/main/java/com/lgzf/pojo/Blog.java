package com.lgzf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-26 - 13:43
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Blog {

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;

    private Long typeId;
    private String description;

    private String tagIds;
    private Long userId;

    private Type type;

    private List<Tag> tags = new ArrayList<>();

    private User user;

    private List<Comment> comments = new ArrayList<>();

}