package com.diary.config;

import com.diary.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

    private final UserServiceImpl userService;

    @Autowired
    public WebSecurityConfig(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                    .antMatchers("/", "/index", "/registration", "/static/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
               //     .defaultSuccessUrl("/main")
                   // .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().and().cors().disable()
                .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuth() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder());
        auth.setUserDetailsService(userService);
        return auth;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
