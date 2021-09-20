package com.fundamientosplatzi.springboot.fundamentos.caseuse;

import com.fundamientosplatzi.springboot.fundamentos.entity.User;
import com.fundamientosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser) {
        return userService.save(newUser);
    }
}
