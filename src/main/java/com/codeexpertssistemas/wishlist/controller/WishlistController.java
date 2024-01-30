package com.codeexpertssistemas.wishlist.controller;

import com.codeexpertssistemas.wishlist.model.Wishlist;
import com.codeexpertssistemas.wishlist.service.WishlistService;
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


    private final WishlistService wishlistService;
    WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }
    @GetMapping
    public @ResponseBody  List<Wishlist> list(){
        return this.wishlistService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> findById(@PathVariable("id") @NotNull @Positive Long id){
        return this.wishlistService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Wishlist createWishlist(@RequestBody @Valid Wishlist wishlist){
        return this.wishlistService.createWishlist(wishlist);
        //return ResponseEntity.status(HttpStatus.CREATED)
                //.body(wishlistRepository.save(wishlist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> updateById(@PathVariable("id") @Valid @NotNull @Positive Long id , @RequestBody Wishlist wishlist){
        return this.wishlistService.update(id,wishlist)
                .map(record -> {
                    return ResponseEntity.ok().body(record);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @NotNull @Positive Long id){
        return this.wishlistRepository.findById(id)
                .map(record -> {
                    this.wishlistRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());

    }*/

    /**
     * Primeiro caminho
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable("id") @NotNull @Positive Long id, @Valid Wishlist wishlist){
        if(wishlistService.delete(id)){
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }

}
