package com.example.myblog.service;

import com.example.myblog.entity.User;
import com.example.myblog.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByEmail(String email);

    void save(WebUser webUser);

}

