package com.example.myblog.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class GlobalExceptionHandler {

    //@ExceptionHandler(AuthenticationException.class)
    public String handleUsernameNotFoundException(Model model, AuthenticationException ex) {
//        System.out.println("error in handler");
//        model.addAttribute("errorMessage", ex.getMessage());
//        return "login"; // Or whatever your login page is
        return null;
    }

    // Add more exception handlers for other exceptions if needed
}
