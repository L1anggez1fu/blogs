package com.lgzf.dao;

import com.lgzf.dto.*;
import com.lgzf.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-28 - 19:23
 **/
@Mapper
@Repository
public interface BlogDao {

    ShowBlog getBlogById(Long id);

    List<BlogQuery> getAllBlog();

    int saveBlog(Blog blog);

    int saveBlogAndTag(BlogAndTag blogAndTag);

    int updateBlog(ShowBlog showBlog);

    int deleteBlog(Long id);

    int deleteBlogAndTag(Long Id);

    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    List<HomePageBlog> getSearchBlog(String query);

    List<HomePageBlog> getHomePageBlog();

    List<RecommendBlog> getAllRecommendBlog();

    DetailedBlog getDetailedBlog(Long id);

    List<HomePageBlog> getByTypeId(Long typeId);

    List<HomePageBlog> getByTagId(Long tagId);

    List<String> getArchivesYear();

    List<Blog> getBlogByYear(String year);
}
