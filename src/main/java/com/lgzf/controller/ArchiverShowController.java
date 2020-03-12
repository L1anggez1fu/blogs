package com.lgzf.controller;

import com.github.pagehelper.PageInfo;
import com.lgzf.dto.BlogQuery;
import com.lgzf.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auther lgzf
 * @data 2020-03-08 - 13:50
 **/
@Controller
public class ArchiverShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        model.addAttribute("archiveMap", blogService.archiveBlog());
        List<BlogQuery> allBlog = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        return "archives";
    }
}
