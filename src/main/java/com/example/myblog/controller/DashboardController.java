package com.example.myblog.controller;

import com.example.myblog.entity.Post;
import com.example.myblog.entity.User;
import com.example.myblog.service.PostService;
import com.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private UserService userService;
    private PostService postService;

    @Autowired
    public DashboardController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping({"", "/"})
    public String home(Authentication authentication, Model theModel) {
        User user = userService.findByEmail(authentication.getName());
        List<Post> posts = postService.findPostsByUserId(Math.toIntExact(user.getId()));
        theModel.addAttribute("posts", posts);

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

    @GetMapping({"/post/new"})
    public String addPost(Authentication authentication, Model theModel) {
        theModel.addAttribute("post", new Post());
        return "dashboard/post/add-post";
    }

    @PostMapping({"/post/save-post"})
    public String savePost(Authentication authentication,
                           @ModelAttribute("post") Post thePost,
                           Model theModel) {

        User user = userService.findByEmail(authentication.getName());
        thePost.setCreatedAt(new Date());
        thePost.setUpdatedAt(new Date());
        thePost.setUser(user);
        postService.save(thePost);

        return "redirect:/dashboard";
    }

}
