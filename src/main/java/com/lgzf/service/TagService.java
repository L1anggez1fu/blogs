package com.lgzf.service;

import com.lgzf.pojo.Tag;

import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-27 - 16:55
 **/

public interface TagService {

    int saveTag(Tag tag);

    int deleteTag(Long id);

    int updateTag(Tag tag);

    Tag getById(Long id);

    Tag getByName(String name);

    List<Tag> getAllTag();

    List<Tag> getTagByString(String text);

    List<Tag> getAllTagNum();

}
