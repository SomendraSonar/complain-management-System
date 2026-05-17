package com.example.complain.managment.controller;

import org.springframework.web.bind.annotation.*;

import com.example.complain.managment.entity.User;
import com.example.complain.managment.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final UserRepository repo;

    public AuthController(UserRepository repo){
        this.repo = repo;
    }

    // Register

    @PostMapping("/register")
    public User register(
            @RequestBody User user){

        user.setRole("USER");

        return repo.save(user);

    }

    // Login

    @PostMapping("/login")
    public User login(
            @RequestBody User user){

        return repo
                .findByEmail(
                        user.getEmail())

                .filter(u ->
                        u.getPassword()
                         .equals(
                         user.getPassword()))

                .orElse(null);

    }
}