package com.codeexpertssistemas.wishlist.controller;

import com.codeexpertssistemas.wishlist.model.Wishlist;
import com.codeexpertssistemas.wishlist.repository.WishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Wishlist createWishlist(@RequestBody Wishlist wishlist){
        return this.wishlistRepository.save(wishlist);
        //return ResponseEntity.status(HttpStatus.CREATED)
                //.body(wishlistRepository.save(wishlist));
    }

}
