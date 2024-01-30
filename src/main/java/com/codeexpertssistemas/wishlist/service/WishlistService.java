package com.codeexpertssistemas.wishlist.service;

import com.codeexpertssistemas.wishlist.model.Wishlist;
import com.codeexpertssistemas.wishlist.repository.WishlistRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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

    public Optional<Wishlist> findById(@PathVariable("id") @NotNull @Positive Long id){
        return this.wishlistRepository.findById(id);
    }

    public Wishlist createWishlist(@Valid Wishlist wishlist) {
        return this.wishlistRepository.save(wishlist);
    }

    public Optional<Wishlist> update(@NotNull @Positive Long id , @Valid Wishlist wishlist){
        return this.wishlistRepository.findById(id)
                .map(record -> {
                    record.setItem(wishlist.getItem());
                    record.setLink(wishlist.getLink());
                    return wishlistRepository.save(record);

                });
    }

    public boolean delete(@PathVariable("id") @NotNull @Positive Long id){
        return this.wishlistRepository.findById(id)
                .map(record -> {wishlistRepository.deleteById(id);return true;})
                .orElse(false);

    }
}
