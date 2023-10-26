package com.example.myblog.controller;

import com.example.myblog.entity.Post;
import com.example.myblog.entity.User;
import com.example.myblog.service.PostService;
import com.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
                           Model theModel,
                           RedirectAttributes redirAttrs) {

        User user = userService.findByEmail(authentication.getName());

        String slug = thePost.getTitle().trim().replaceAll("[^a-zA-Z0-9\s]", "");
        slug = slug.replaceAll(" ", "-");

        int postsCount = postService.getPostsCountBySlug(slug);

        if (postsCount > 0) {
            slug += "-" + postsCount;
        }

        thePost.setSlug(slug);

        thePost.setCreatedAt(new Date());
        thePost.setUpdatedAt(new Date());
        thePost.setUser(user);
        //System.out.println("saving post");
        //System.out.println(thePost);
        try {
            postService.save(thePost);

        } catch (Exception e) {
            //System.out.println(e.getMessage());
            redirAttrs.addFlashAttribute("flash_error_message", e.getMessage());
            return "redirect:/dashboard/post/new";
        }

        redirAttrs.addFlashAttribute("flash_message", "Post added successfully!");

        return "redirect:/dashboard";
    }

    @GetMapping({"/post/edit/{id}"})
    public String editPost(Authentication authentication, @PathVariable int id, Model theModel) {

        User user = userService.findByEmail(authentication.getName());
        Post post = postService.findById(id);

        if (user == null || post == null) {
            return "redirect:/dashboard";
        }

        if (!Objects.equals(user.getId(), post.getUser().getId())) {
            return "redirect:/dashboard";
        }

        theModel.addAttribute("post", post);

        return "dashboard/post/edit-post";
    }

    @PostMapping({"/post/update-post"})
    public String updatePost(
            Authentication authentication,
            @ModelAttribute("post") Post webPost,
            RedirectAttributes redirAttrs) {

        User user = userService.findByEmail(authentication.getName());
        Post post = postService.findById(Math.toIntExact(webPost.getId()));

        if (user == null || post == null) {
            return "redirect:/dashboard";
        }

        if (!Objects.equals(user.getId(), post.getUser().getId())) {
            return "redirect:/dashboard";
        }

        post.setTitle(webPost.getTitle());
        post.setContent(webPost.getContent());
        post.setExcerpt(webPost.getExcerpt());
        post.setFeaturedImgUrl(webPost.getFeaturedImgUrl());
        post.setStatus(webPost.getStatus());
        post.setUpdatedAt(new Date());

//        System.out.println("updating post");
//        System.out.println(post);
//        System.out.println(post.getUser());
        postService.save(post);

        redirAttrs.addFlashAttribute("flash_message", "Post updated successfully!");

        return "redirect:/dashboard";
    }

    @GetMapping({"/post/delete/{id}"})
    public String deletePost(
            Authentication authentication,
            @PathVariable int id,
            RedirectAttributes redirAttrs) {

        User user = userService.findByEmail(authentication.getName());
        Post post = postService.findById(id);

        if (user == null || post == null) {
            return "redirect:/dashboard";
        }

        //check if post is belong to the current logged in user
        if (!Objects.equals(user.getId(), post.getUser().getId())) {
            return "redirect:/dashboard";
        }

        postService.deleteById(id);

        redirAttrs.addFlashAttribute("flash_message", "Post deleted successfully!");

        return "redirect:/dashboard";
    }

}
