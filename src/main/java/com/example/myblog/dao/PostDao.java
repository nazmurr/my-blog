package com.example.myblog.dao;

import com.example.myblog.entity.Post;

import java.util.List;

public interface PostDao {

    void save(Post thePost);

    Post findById(int postId);

    void deleteById(int postId);

    List<Post> findPostsByUserId(int userId);

}
