package com.example.myblog.dao;

import com.example.myblog.entity.User;

public interface UserDao {

    User findByEmail(String email);

    void save(User theUser);
}
