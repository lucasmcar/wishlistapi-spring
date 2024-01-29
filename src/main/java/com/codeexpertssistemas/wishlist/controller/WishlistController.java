package com.codeexpertssistemas.wishlist.controller;

import com.codeexpertssistemas.wishlist.model.Wishlist;
import com.codeexpertssistemas.wishlist.repository.WishlistRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
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

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> findById(@PathVariable("id") @NotNull @Positive Long id){
        return this.wishlistRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Wishlist createWishlist(@RequestBody @Valid Wishlist wishlist){
        return this.wishlistRepository.save(wishlist);
        //return ResponseEntity.status(HttpStatus.CREATED)
                //.body(wishlistRepository.save(wishlist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> updateById(@PathVariable("id") @Valid @NotNull @Positive Long id , @RequestBody Wishlist wishlist){
        return this.wishlistRepository.findById(id)
                .map(record -> {
                    record.setItem(wishlist.getItem());
                    record.setLink(wishlist.getLink());
                    Wishlist updated = wishlistRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @NotNull @Positive Long id){
        return this.wishlistRepository.findById(id)
                .map(record -> {
                    this.wishlistRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());

    }

}
