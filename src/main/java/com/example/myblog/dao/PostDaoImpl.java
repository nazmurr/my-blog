package com.example.myblog.dao;

import com.example.myblog.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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

    @Override
    public List<Post> findAllPosts() {

        TypedQuery<Post> query = entityManager.createQuery(
                "from Post order by id desc", Post.class);

        List<Post> posts = query.getResultList();

        return posts;
    }

    @Override
    public List<Post> findAllPosts(String postStatus, int pageSize, int pageNo) {

        TypedQuery<Post> query = entityManager.createQuery(
                "from Post where status = :data order by id desc", Post.class);

        query.setParameter("data", postStatus);

        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);

        List<Post> posts = query.getResultList();

        return posts;
    }

    @Override
    public int getPostsCount() {

        String countQ = "Select count (p.id) from Post p";
        Query query = entityManager.createQuery(countQ);

        Long postsCount = (Long) query.getSingleResult();

        return postsCount.intValue();
    }

    @Override
    public int getPostsCount(String postStatus) {

        String countQ = "Select count (p.id) from Post p where p.status = :data";
        Query query = entityManager.createQuery(countQ);
        query.setParameter("data", postStatus);

        Long postsCount = (Long) query.getSingleResult();

        return postsCount.intValue();
    }

    @Override
    public int getPostsCountBySlug(String postSlug) {

        String countQ = "Select count (p.id) from Post p where p.slug like concat(:data,'%')";
        Query query = entityManager.createQuery(countQ);
        query.setParameter("data", postSlug);

        Long postsCount = (Long) query.getSingleResult();

        return postsCount.intValue();
    }

    @Override
    public Post findBySlug(String postSlug) {

        Post post;

        TypedQuery<Post> query =  entityManager.createQuery(
                "from Post where slug = :data", Post.class);

        query.setParameter("data", postSlug);

        try {
            post = query.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            post = null;
        }

        return post;
    }
}
