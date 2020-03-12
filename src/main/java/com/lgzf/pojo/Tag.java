package com.lgzf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-26 - 14:57
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tag {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();


}
