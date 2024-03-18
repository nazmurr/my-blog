package com.example.myblog.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/dashboard";
    }

    // add request mapping for /access-denied

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @GetMapping("/login-error")
    public ModelAndView loginError(HttpServletRequest request) {
        String errorMessage = request.getSession().getAttribute("errorMessage").toString();
        //System.out.println(request.getSession().getAttribute("errorMessage"));
//        AuthenticationException exception = (AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
//        String errorMessage = exception.getMessage();
//        System.out.println(exception.toString());
//        System.out.println(errorMessage);
//        if (exception instanceof UsernameNotFoundException) {
//            // Set a custom error message in the model or session
//            System.out.println("invalid username or password");
//        } else {
//            // Handle other exceptions (optional)
//        }
//
        ModelAndView modelAndView = new ModelAndView("login"); // Specify the error template name
        modelAndView.addObject("errorMessage", errorMessage);

        return modelAndView;
        //return "login";
    }

//    @ExceptionHandler(RuntimeException.class)
//    public String handleUsernameNotFoundException(RuntimeException ex, Model model) {
//        System.out.println(ex.getMessage());
//        model.addAttribute("errorMessage", ex.getMessage());
//        return "login";
//    }

}

