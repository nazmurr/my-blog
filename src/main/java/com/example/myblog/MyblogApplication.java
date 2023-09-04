package com.example.myblog;

import com.example.myblog.dao.PostDao;
import com.example.myblog.dao.UserDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyblogApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(PostDao postDao, UserDao userDao) {
		return runner -> {
//			Post post = new Post("my second post", "my post content", "published", new Date(), new Date());
//			User user = userDao.findByEmail("nazmur.r@gmail.com");
//			post.setUser(user);
//			postDao.save(post);
		};
	}

}
