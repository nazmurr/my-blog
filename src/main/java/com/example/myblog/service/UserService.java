package com.example.myblog.service;

import com.example.myblog.dto.UserWithPostCountDTO;
import com.example.myblog.entity.User;
import com.example.myblog.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User findById(Long id);

    void save(WebUser webUser);

    void save(User user);

    List<User> findAllUsers();

    List<UserWithPostCountDTO> findAllUsersWithPostCount();

}

