package com.codeexpertssistemas.wishlist.controller;

import com.codeexpertssistemas.wishlist.dto.WishlistDTO;
import com.codeexpertssistemas.wishlist.service.WishlistService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
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
    public List<WishlistDTO> list(){
        return this.wishlistService.list();
    }

    @GetMapping("/{id}")
    public WishlistDTO findById(@PathVariable("id") @NotNull @Positive Long id){
        return this.wishlistService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public WishlistDTO createWishlist(@RequestBody @Valid WishlistDTO wishlist){
        return this.wishlistService.createWishlist(wishlist);
        //return ResponseEntity.status(HttpStatus.CREATED)
                //.body(wishlistRepository.save(wishlist));
    }

    @PutMapping("/{id}")
    public WishlistDTO updateById(@PathVariable("id") @Valid @NotNull @Positive Long id , @RequestBody WishlistDTO wishlist){
        return this.wishlistService.update(id,wishlist);
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
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @NotNull @Positive Long id){
        wishlistService.delete(id);
    }

}
