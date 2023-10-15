package com.example.myblog.service;

import com.example.myblog.dao.PostDao;
import com.example.myblog.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public Post findById(int postId) {
        return postDao.findById(postId);
    }

    @Override
    public void deleteById(int postId) {
        postDao.deleteById(postId);
    }

    @Override
    public List<Post> findPostsByUserId(int userId) {
        return postDao.findPostsByUserId(userId);
    }

    @Override
    public List<Post> findAllPosts() {
        return postDao.findAllPosts();
    }

    @Override
    public List<Post> findAllPosts(String postStatus, int pageSize, int pageNo) {
        return postDao.findAllPosts(postStatus, pageSize, pageNo);
    }

    @Override
    public int getPostsCount() {
        return postDao.getPostsCount();
    }

    @Override
    public int getPostsCount(String postStatus) {
        return postDao.getPostsCount(postStatus);
    }

    @Override
    public int getPostsCountBySlug(String postSlug) {
        return postDao.getPostsCountBySlug(postSlug);
    }

    @Override
    public Post findBySlug(String postSlug) {
        return postDao.findBySlug(postSlug);
    }

}
