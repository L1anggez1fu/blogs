package com.lgzf.controller.admin;

import com.lgzf.pojo.User;
import com.lgzf.service.BlogService;
import com.lgzf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @auther lgzf
 * @data 2020-02-26 - 16:21
 **/

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        HttpSession session,
                        RedirectAttributes attributes) {

        User user = userService.checkUser(username, password);

        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/login-success";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

    /*底部最新博客*/
    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.getRecommendedBlog());
        return "_fragments :: newblogList";
    }
}
