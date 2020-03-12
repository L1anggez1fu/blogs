package com.lgzf.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgzf.pojo.Type;
import com.lgzf.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @auther lgzf
 * @data 2020-02-27 - 13:46
 **/
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /*获得所有分页*/
    @GetMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        /*分页插件*/
        PageHelper.startPage(pageNum, 3);
        List<Type> types = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    /*新增页面*/
    @GetMapping("/types/input")
    public String input() {
        return "admin/types-input";
    }

    /*添加分类*/
    @PostMapping("/types/add")
    public String post(Type type, RedirectAttributes attributes) {
        /*校验是否存在该分类*/
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的类");
            return "redirect:/admin/types/input";
        } else {
            attributes.addFlashAttribute("message", "添加成功");
        }
        //添加操作
        typeService.saveType(type);
        return "redirect:/admin/types";
    }

    /*跳转到更新页面*/
    @GetMapping("/types/{id}/input")
    public String updateType(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-update";
    }

    /*更新操作*/
    @PostMapping("/types/update")
    public String updatePost(Type type, RedirectAttributes attributes) {
        /*校验是否存在该分类*/
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "已存在该分类");
            return "redirect:/admin/types";
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        //更新操作
        typeService.updateType(type);
        return "redirect:/admin/types";
    }

    /*删除*/
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

}
