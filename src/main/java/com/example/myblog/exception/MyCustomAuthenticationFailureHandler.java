package com.example.myblog.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class MyCustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;
        //AuthenticationException lastException = (AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        String exceptionClassName = exception.getClass().getName();

        // Log the class name of the exception
        System.out.println("Exception class name: " + exceptionClassName);
        if (exception instanceof UsernameNotFoundException) {
            errorMessage = exception.getMessage();
        } else {
            errorMessage = "Invalid credentials.";
        }
        System.out.println("in hanlder java");
        //request.setAttribute("errorMessage", errorMessage); // Set error message as model attribute
        request.getSession().setAttribute("errorMessage", errorMessage);
        response.sendRedirect("/login-error");
        //request.getRequestDispatcher("/login-error").forward(request, response);
    }
}
