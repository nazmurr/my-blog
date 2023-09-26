package com.example.myblog.dao;

import com.example.myblog.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PostDaoImpl implements PostDao {
    private EntityManager entityManager;

    @Autowired
    public PostDaoImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Post thePost) {
        entityManager.merge(thePost);
    }

    @Override
    public Post findById(int postId) {
        return entityManager.find(Post.class, postId);
    }

    @Override
    @Transactional
    public void deleteById(int postId) {
        Post post = entityManager.find(Post.class, postId);
        entityManager.remove(post);
    }

    @Override
    public List<Post> findPostsByUserId(int userId) {

        TypedQuery<Post> query = entityManager.createQuery(
                "from Post where user.id = :data order by id desc", Post.class);
        query.setParameter("data", userId);

        List<Post> posts = query.getResultList();

        return posts;
    }
}
