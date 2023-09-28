package com.example.myblog;

import com.example.myblog.entity.Post;
import com.example.myblog.entity.User;
import com.example.myblog.service.PostService;
import com.example.myblog.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class MyblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyblogApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(PostService postService, UserService userService) {
		return runner -> {
			//createPost(postService, userService);
			//updatePost(postService, 3);
			//deletePost(postService, 8);
			//getPostsByUserId(postService, 1);
			//getAllPosts(postService);
			getPostsCount(postService);
		};
	}

	private void createPost(PostService postService, UserService userService) {
		Post post = new Post("nice post", "thanks for writing it", "draft", new Date(), new Date());
		User user = userService.findByEmail("nazmur.r@gmail.com");
		post.setUser(user);
		postService.save(post);
	}

	private void updatePost(PostService postService, int postId) {
		Post post = postService.findById(postId);
		post.setTitle("This is a updated post2");
		post.setContent("this is a updated content");
		post.setStatus("unpublished");
		post.setUpdatedAt(new Date());
		postService.save(post);
	}

	private void deletePost(PostService postService, int postId) {
		postService.deleteById(postId);
	}

	private void getPostsByUserId(PostService postService, int userId) {
		List<Post> posts = postService.findPostsByUserId(userId);
		System.out.println(posts);

	}

	private void getAllPosts(PostService postService) {
		List<Post> posts = postService.findAllPosts("published", 2, 1);
		System.out.println(posts);
	}

	private void getPostsCount(PostService postService) {
		System.out.println("total posts: " + postService.getPostsCount());
		System.out.println("published posts: " + postService.getPostsCount("published"));
	}

}
