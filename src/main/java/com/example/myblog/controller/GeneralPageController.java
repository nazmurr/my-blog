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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GeneralPageController {

    private PostService postService;

    private UserService userService;

    @Autowired
    public GeneralPageController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }

    @GetMapping({"/blog", "/blog/{page}"})
    public String showBlog(Model theModel, @PathVariable(required = false) Integer page) {

        int pageSize = 5;
        int postsCount = postService.getPostsCount("published");
        int lastPageNumber = (int) (Math.ceil((double) postsCount / (double) pageSize));

        List<Post> posts = postService.findAllPosts("published", pageSize, page == null ? 1 : page);

        theModel.addAttribute("posts", posts);
        theModel.addAttribute("lastPageNumber", lastPageNumber);
        theModel.addAttribute("currentPageNumber", page == null ? 1 : page);

        return "blog";
    }

//    @GetMapping("/post/{id}")
//    public String showPost(@PathVariable int id, Model theModel) {
//
//        Post post = postService.findById(id);
//
//        if ((post == null) || (!post.getStatus().equals("published"))) {
//            return "error";
//        }
//
//        theModel.addAttribute("post", post);
//
//        return "post";
//    }

    @GetMapping("/post/{slug}")
    public String showPost(Authentication authentication, @PathVariable String slug, Model theModel) {

        String postStatus = "published";
        User user;

        Post post = postService.findBySlug(slug);

        if (authentication != null) {
            user = userService.findByEmail(authentication.getName());

            //allow logged-in users to see their own posts no matter the post status
            if (user.getId().equals(post.getUser().getId())) {
                postStatus = post.getStatus();
            }
        }

        if ((post == null) || (!post.getStatus().equals(postStatus))) {
            return "error";
        }

        theModel.addAttribute("post", post);

        return "post";
    }
}
