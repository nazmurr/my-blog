package com.example.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralPageController {
    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }
}
