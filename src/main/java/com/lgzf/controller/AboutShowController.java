package com.lgzf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther lgzf
 * @data 2020-03-08 - 14:48
 **/
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about() {
        return "aboutme";
    }
}
