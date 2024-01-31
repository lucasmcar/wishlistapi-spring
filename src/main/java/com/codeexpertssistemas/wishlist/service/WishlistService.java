package com.codeexpertssistemas.wishlist.service;

import com.codeexpertssistemas.wishlist.exception.RecordNotFoundException;
import com.codeexpertssistemas.wishlist.model.Wishlist;
import com.codeexpertssistemas.wishlist.repository.WishlistRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Validated
@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    public WishlistService(WishlistRepository repository){
        this.wishlistRepository = repository;
    }

    public List<Wishlist> list(){
        return this.wishlistRepository.findAll();
    }

    public Wishlist findById(@PathVariable("id") @NotNull @Positive Long id){
        return this.wishlistRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Wishlist createWishlist(@Valid Wishlist wishlist) {
        return this.wishlistRepository.save(wishlist);
    }

    public Wishlist update(@NotNull @Positive Long id , @Valid Wishlist wishlist){
        return this.wishlistRepository.findById(id)
                .map(record -> {
                    record.setItem(wishlist.getItem());
                    record.setLink(wishlist.getLink());
                    return wishlistRepository.save(record);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id){

        wishlistRepository.delete(
                this.wishlistRepository.findById(id)
                        .orElseThrow(()-> new RecordNotFoundException(id)));
        /*this.wishlistRepository.findById(id)
                .map(record -> {wishlistRepository.deleteById(id);return true;})
                .orElseThrow(()-> new RecordNotFoundException(id));*/

    }
}
