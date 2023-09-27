package com.example.myblog.controller;

import com.example.myblog.entity.Post;
import com.example.myblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GeneralPageController {

    private PostService postService;

    @Autowired
    public GeneralPageController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }

    @GetMapping("/blog")
    public String showBlog(Model theModel) {

        List<Post> posts = postService.findAllPosts("published");
        theModel.addAttribute("posts", posts);

        return "blog";
    }

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable int id, Model theModel) {

        Post post = postService.findById(id);

        if ((post == null) || (!post.getStatus().equals("published"))) {
            return "error";
        }

        theModel.addAttribute("post", post);

        return "post";
    }
}
