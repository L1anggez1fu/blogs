package com.lgzf.dao;

import com.lgzf.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-27 - 16:57
 **/
@Mapper
@Repository
public interface TagDao {

    int saveTag(Tag tag);

    int deleteTag(Long id);

    int updateTag(Tag tag);

    Tag getById(Long id);

    Tag getByName(String name);

    List<Tag> getAllTag();

    List<Tag> getAllTagNum();
}
