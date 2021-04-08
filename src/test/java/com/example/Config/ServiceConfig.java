package com.example.Config;

import com.example.dao.UserDao;
import com.example.service.UserService;
import org.springframework.context.annotation.Bean;

public class ServiceConfig {
    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }
}
