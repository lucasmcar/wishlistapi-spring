package com.codeexpertssistemas.wishlist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public String hello(){
        return "Hello";
    }
}
