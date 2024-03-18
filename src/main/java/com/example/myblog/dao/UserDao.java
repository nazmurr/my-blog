package com.example.myblog.dao;

import com.example.myblog.dto.UserWithPostCountDTO;
import com.example.myblog.entity.Post;
import com.example.myblog.entity.User;

import java.util.List;

public interface UserDao {

    User findByEmail(String email);

    User findById(Long id);

    void save(User theUser);

    List<User> findAllUsers();

    List<UserWithPostCountDTO> findAllUsersWithPostCount();
}
