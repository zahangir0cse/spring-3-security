package com.crackcode.zahangir.controller;

import com.crackcode.zahangir.annotations.ApiController;
import com.crackcode.zahangir.entity.User;
import com.crackcode.zahangir.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@ApiController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Long id){
        return userService.get(id);
    }
}
