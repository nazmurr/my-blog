package com.example.myblog.security;

import com.example.myblog.exception.GlobalExceptionHandler;
import com.example.myblog.exception.MyCustomAuthenticationFailureHandler;
import com.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
//                                .requestMatchers(
//                                        "/",
//                                        "/about",
//                                        "/register",
//                                        "/processRegistrationForm",
//                                        "/post/**").permitAll()
                                //.requestMatchers("/login", "/register", "/processRegistrationForm").anonymous()
                                .requestMatchers("/dashboard/all-users", "/dashboard/all-posts").hasRole("ADMIN")
                                .requestMatchers("/dashboard/**").hasAnyRole("SUBSCRIBER", "ADMIN")
                                //.anyRequest().authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                //.failureUrl("/login-error")
                                //.failureHandler(new MyCustomAuthenticationFailureHandler())
                                .usernameParameter("email")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/dashboard", true)
                                .permitAll()

                )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );

        return http.build();
    }

}
