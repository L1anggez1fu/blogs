package com.lgzf.service.impl;

import com.lgzf.dao.TagDao;
import com.lgzf.pojo.Tag;
import com.lgzf.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-27 - 16:56
 **/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public Tag getById(Long id) {
        return tagDao.getById(id);
    }

    @Override
    public Tag getByName(String name) {
        return tagDao.getByName(name);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long aLong : longs) {
            tags.add(tagDao.getById(aLong));
        }
        return tags;
    }

    @Override
    public List<Tag> getAllTagNum() {
        return tagDao.getAllTagNum();
    }

    /*将id转换为list*/
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
