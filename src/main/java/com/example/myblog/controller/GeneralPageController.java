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
    public String showPost(@PathVariable String slug, Model theModel) {

        Post post = postService.findBySlug(slug);

        if ((post == null) || (!post.getStatus().equals("published"))) {
            return "error";
        }

//        if (post != null) {
//            Pattern pattern = Pattern.compile("\\d+$", Pattern.CASE_INSENSITIVE);
//            Matcher matcher = pattern.matcher(post.getSlug());
//            boolean matchFound = matcher.find();
//            System.out.println(matchFound);
//
//
//            if (matchFound) {
//                int index = Integer.parseInt(matcher.group(0)) + 1;
//                System.out.println(index);
//                System.out.println("my-new-slug-" + index);
//
//            }
//        }

        theModel.addAttribute("post", post);

        return "post";
    }
}
