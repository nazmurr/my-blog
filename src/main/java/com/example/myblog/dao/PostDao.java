package com.example.myblog.dao;

import com.example.myblog.entity.Post;

import java.util.List;

public interface PostDao {

    void save(Post thePost);

    Post findById(int postId);

    void deleteById(int postId);

    List<Post> findPostsByUserId(int userId);

    List<Post> findAllPosts();

    List<Post> findAllPosts(String postStatus, int pageSize, int pageNo);

    int getPostsCount();

    int getPostsCount(String postStatus);

    int getPostsCountBySlug(String postSlug);

    Post findBySlug(String postSlug);

}
