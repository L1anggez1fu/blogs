package com.lgzf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @auther lgzf
 * @data 2020-03-02 - 17:34
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HomePageBlog {
        //Blog
        private Long id;
        private String title;
        private String firstPicture;
        private Integer views;
        private Date updateTime;
        private String description;

        //Type
        private String typeName;

        //User
        private String nickname;
        private String avatar;

}
