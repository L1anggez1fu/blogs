package com.lgzf.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgzf.pojo.Tag;
import com.lgzf.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-27 - 17:00
 **/
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /*获得所有标签*/
    @GetMapping("/tags")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        /*分页插件*/
        PageHelper.startPage(pageNum, 3);
        List<Tag> tags = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    /*新增页面*/
    @GetMapping("/tags/input")
    public String input() {
        return "admin/tags-input";
    }

    /*添加标签*/
    @PostMapping("/tags/add")
    public String post(Tag tag, RedirectAttributes attributes) {
        /*校验是否存在该分类*/
        Tag tag1 = (Tag) tagService.getByName(tag.getName());
        if (tag1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的类");
            return "redirect:/admin/tag/input";
        } else {
            attributes.addFlashAttribute("message", "添加成功");
        }
        //添加操作
        tagService.saveTag(tag);
        return "redirect:/admin/tags";
    }

    /*跳转到更新页面*/
    @GetMapping("/tags/{id}/input")
    public String updateTag(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getById(id));
        return "admin/tags-update";
    }

    /*更新操作*/
    @PostMapping("/tags/update")
    public String updatePost(Tag tag, RedirectAttributes attributes) {
        /*校验是否存在该分类*/
        Tag tag1 = (Tag) tagService.getByName(tag.getName());
        if (tag1 != null) {
            attributes.addFlashAttribute("message", "已存在该标签");
            return "redirect:/admin/tags";
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        //更新操作
        tagService.updateTag(tag);
        return "redirect:/admin/tags";
    }

    /*删除*/
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
