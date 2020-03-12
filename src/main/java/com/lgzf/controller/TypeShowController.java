package com.lgzf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgzf.dto.HomePageBlog;
import com.lgzf.pojo.Type;
import com.lgzf.service.BlogService;
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
 * @data 2020-03-06 - 17:37
 **/
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @PathVariable Long id, Model model) {
        List<Type> types = typeService.getAllTypeNum();
        /*默认显示第一个分类*/
        if (id == -1) {
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        List<HomePageBlog> blogs = blogService.getByTypeId(id);
        PageHelper.startPage(pageNum, 100);
        PageInfo<HomePageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
