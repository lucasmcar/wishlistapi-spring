package com.codeexpertssistemas.wishlist.controller;

import com.codeexpertssistemas.wishlist.model.Wishlist;
import com.codeexpertssistemas.wishlist.repository.WishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/api/wishlist")

public class WishlistController {

    private final WishlistRepository wishlistRepository;
    public WishlistController(WishlistRepository repository){
        this.wishlistRepository = repository;
    }
    @GetMapping
    public List<Wishlist> list(){
        return this.wishlistRepository.findAll();
    }
}
