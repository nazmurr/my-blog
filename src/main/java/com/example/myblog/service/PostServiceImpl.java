package com.example.myblog.service;

import com.example.myblog.dao.PostDao;
import com.example.myblog.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImpl implements PostService {
    private PostDao postDao;

    @Autowired
    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public void save(Post post) {
        postDao.save(post);

    }
}
