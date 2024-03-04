package com.example.myblog.dao;

import com.example.myblog.dto.UserWithPostCountDTO;
import com.example.myblog.entity.Post;
import com.example.myblog.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    //@PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public User findByEmail(String theEmail) {

        // retrieve/read from database using username
        TypedQuery<User> theQuery = entityManager.createQuery("from User where email=:uEmail", User.class);
        theQuery.setParameter("uEmail", theEmail);

        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            System.out.println("in UserDaoImpl: " + e.getMessage());
            theUser = null;
        }

        return theUser;
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void save(User theUser) {

        // create the user
        entityManager.merge(theUser);
    }

    @Override
    public List<User> findAllUsers() {

        TypedQuery<User> query = entityManager.createQuery(
                "from User order by createdAt desc", User.class);

        List<User> users = query.getResultList();

        return users;
    }

    @Override
    public List<UserWithPostCountDTO> findAllUsersWithPostCount() {

        TypedQuery<UserWithPostCountDTO> query = entityManager.createQuery(
                "SELECT NEW " +
                        UserWithPostCountDTO.class.getName() +
                        "(u.id, u.firstName, u.lastName, u.email, u.enabled, u.createdAt, COUNT(p)) " +
                        "FROM User u " +
                        "LEFT JOIN u.posts p " +
                        "GROUP BY u.id", UserWithPostCountDTO.class);

        return query.getResultList();
    }


}
