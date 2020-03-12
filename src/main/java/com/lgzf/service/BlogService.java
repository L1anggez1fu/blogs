package com.lgzf.service;

import com.lgzf.dto.*;
import com.lgzf.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * @auther lgzf
 * @data 2020-02-28 - 19:23
 **/
public interface BlogService {

    List<BlogQuery> getAllBlog();

    ShowBlog getBlogById(Long id);

    int saveBlog(Blog blog);

    int updateBlog(ShowBlog showBlog);

    int deleteBlog(Long id);

    //修改recommend,因为recommend从前台接收只能接收字符串，但数据库中的Integer类型，所以转一下
    void transformRecommend(SearchBlog searchBlog);

    /*获得搜索到的blog*/
    List<HomePageBlog> getSearchBlog(String query);

    /*通过搜索blog*/
    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

    /*首页获取博客*/
    List<HomePageBlog> getAllHomePageBlog();

    /*获取推荐博客*/
    List<RecommendBlog> getRecommendedBlog();

    /*获得详细博客*/
    DetailedBlog getDetailedBlog(Long id);

    /*根据TypeId获取博客*/
    List<HomePageBlog> getByTypeId(Long typeId);

    /*根据TagId获取博客*/
    List<HomePageBlog> getByTagId(Long tagId);

    /*归档*/
    Map<String, List<Blog>> archiveBlog();
}
