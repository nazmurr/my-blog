package com.example.myblog.service;

import com.example.myblog.entity.Post;

import java.util.List;

public interface PostService {
    void save(Post post);

    Post findById(int postId);

    void deleteById(int postId);

    List<Post> findPostsByUserId(int userId);
}
