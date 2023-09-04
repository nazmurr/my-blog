package com.example.myblog.dao;

import com.example.myblog.entity.Post;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
