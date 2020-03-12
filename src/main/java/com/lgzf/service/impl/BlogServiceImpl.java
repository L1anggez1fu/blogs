package com.lgzf.service.impl;

import com.lgzf.dao.BlogDao;
import com.lgzf.dto.*;
import com.lgzf.exception.NotFoundException;
import com.lgzf.pojo.Blog;
import com.lgzf.pojo.Tag;
import com.lgzf.service.BlogService;
import com.lgzf.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther lgzf
 * @data 2020-02-28 - 19:23
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public List<BlogQuery> getAllBlog() {
        List<BlogQuery> allBlogQuery = blogDao.getAllBlog();
        return allBlogQuery;
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return blogDao.saveBlog(blog);
    }

    @Override
    public int updateBlog(ShowBlog blog) {
        blog.setUpdateTime(new Date());
        return blogDao.updateBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        blogDao.deleteBlogAndTag(id);
        blogDao.deleteBlog(id);
        return 1;
    }

    @Override
    public void transformRecommend(SearchBlog searchBlog) {
        if (!"".equals(searchBlog.getRecommend()) && null != searchBlog.getRecommend()) {
            searchBlog.setRecommend2(1);
        }
    }

    @Override
    public List<HomePageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    @Override
    public List<HomePageBlog> getAllHomePageBlog() {
        return blogDao.getHomePageBlog();
    }

    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        return blogDao.getAllRecommendBlog();
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return detailedBlog;
    }

    @Override
    public List<HomePageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public List<HomePageBlog> getByTagId(Long tagId) {
        return blogDao.getByTagId(tagId);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.getArchivesYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogDao.getBlogByYear(year));
        }
        return map;
    }
}
