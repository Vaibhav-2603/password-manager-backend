package com.opstree.password_manager_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opstree.password_manager_backend.dto.LoginResponse;
import com.opstree.password_manager_backend.entity.User;
import com.opstree.password_manager_backend.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	/**
	 * Authentication Controller
	 * Handles user register & login APIs.
	 */

	
    @Autowired
    private UserService userService;

    // REGISTER API
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user.getUsername(), user.getPassword());
    }


    // LOGIN API (ONLY ONE)
    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {

        User u = userService.login(
                user.getUsername(),
                user.getPassword()
        );

        LoginResponse res = new LoginResponse();
        res.setId(u.getId());
        res.setUsername(u.getUsername());

        return res;
    }
}
