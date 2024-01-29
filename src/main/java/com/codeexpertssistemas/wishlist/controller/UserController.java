package com.codeexpertssistemas.wishlist.controller;

import com.codeexpertssistemas.wishlist.model.User;
import com.codeexpertssistemas.wishlist.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository repo){
        this.userRepository = repo;
    }

    @GetMapping
    public List<User> findAll(){
        return  this.userRepository.findAll();
    }

}
