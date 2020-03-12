package com.lgzf.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgzf.dto.BlogQuery;
import com.lgzf.dto.SearchBlog;
import com.lgzf.dto.ShowBlog;
import com.lgzf.pojo.Blog;
import com.lgzf.pojo.Tag;
import com.lgzf.pojo.Type;
import com.lgzf.pojo.User;
import com.lgzf.service.BlogService;
import com.lgzf.service.TagService;
import com.lgzf.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-27 - 13:01
 **/
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }


    //显示
    @GetMapping("/blogs")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 3);
        List<BlogQuery> allBlog = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);
        return "admin/blogs";
    }

    /*前往新增页面*/
    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    /*添加博客*/
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        blog.setUserId(blog.getUser().getId());
        blogService.saveBlog(blog);
        attributes.addFlashAttribute("message", "新增博客成功");
        return "redirect:/admin/blogs";
    }

    //删除
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

    /*前往博客修改页面*/
    @GetMapping("/blogs/{id}/input")
    public String toUpdate(@PathVariable Long id, Model model) {
        ShowBlog getBlogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getAllType();
        List<Tag> allTag = tagService.getAllTagNum();
        model.addAttribute("blog", getBlogById);
        model.addAttribute("types", allType);
        model.addAttribute("tags", allTag);
        return "admin/blogs-update";
    }

    /*更新博客*/
    @PostMapping("/blogs/update")
    public String editPost(ShowBlog showBlog, RedirectAttributes attributes) {
        blogService.updateBlog(showBlog);
        attributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/blogs";
    }

    /*搜索功能*/
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //将recommend转换一下
        blogService.transformRecommend(searchBlog);
        //动态sql可以解决
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum, 3);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);
        model.addAttribute("message", "查询成功");
        return "admin/blogs";
    }
}
