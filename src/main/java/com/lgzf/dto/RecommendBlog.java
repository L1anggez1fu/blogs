package com.lgzf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther lgzf
 * @data 2020-03-02 - 17:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendBlog {

    private Long id;
    private String title;
    private boolean recommend;

}