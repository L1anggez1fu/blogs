package com.lgzf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgzf.dto.DetailedBlog;
import com.lgzf.dto.HomePageBlog;
import com.lgzf.dto.RecommendBlog;
import com.lgzf.pojo.Tag;
import com.lgzf.pojo.Type;
import com.lgzf.service.BlogService;
import com.lgzf.service.TagService;
import com.lgzf.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-24 - 14:23
 **/
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 6);
        List<HomePageBlog> homePageBlogList = blogService.getAllHomePageBlog();
        List<Type> typeList = typeService.getAllTypeNum();
        List<Tag> tagList = tagService.getAllTagNum();
        List<RecommendBlog> recommendBlogList = blogService.getRecommendedBlog();
        PageInfo<HomePageBlog> pageInfo = new PageInfo<>(homePageBlogList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", tagList);
        model.addAttribute("types", typeList);
        model.addAttribute("recommendedBlogs", recommendBlogList);
        return "index";
    }


    /*搜索栏*/
    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 100);
        List<HomePageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<HomePageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    /*博客详情*/
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }

    /*底部最新博客*/
    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.getRecommendedBlog());
        return "_fragments :: newblogList";
    }
}
