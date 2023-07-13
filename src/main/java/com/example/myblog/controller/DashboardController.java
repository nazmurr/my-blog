package com.example.myblog.controller;

import com.example.myblog.entity.User;
import com.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private UserService userService;

    @Autowired
    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public String home() {
        return "dashboard/home";
    }

    @GetMapping("/my-profile")
    public String myProfile(Authentication authentication, Model theModel) {
        //System.out.println(authentication.getName());
        User user = userService.findByEmail(authentication.getName());
        //System.out.println(user.toString());
        theModel.addAttribute("user", user);

        return "dashboard/user-profile";
    }
}
