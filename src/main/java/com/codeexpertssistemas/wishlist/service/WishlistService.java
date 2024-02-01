package com.codeexpertssistemas.wishlist.service;

import com.codeexpertssistemas.wishlist.dto.WishlistDTO;
import com.codeexpertssistemas.wishlist.dto.mapper.WishlistMapper;
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
import java.util.stream.Collectors;

@Validated
@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final WishlistMapper wishlistMapper;
    public WishlistService(WishlistRepository repository, WishlistMapper wishlistMapper)
    {
        this.wishlistRepository = repository;
        this.wishlistMapper = wishlistMapper;
    }

    public List<WishlistDTO> list(){

        return this.wishlistRepository.findAll().stream().map(
                this.wishlistMapper::toDTO
        ).collect(Collectors.toList());
    }

    public WishlistDTO findById(@PathVariable("id") @NotNull @Positive Long id){
        return this.wishlistRepository.findById(id).map(this.wishlistMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public WishlistDTO createWishlist(@Valid WishlistDTO wishlist) {

        return this.wishlistMapper.toDTO(this.wishlistRepository.save(wishlistMapper.toEntity(wishlist)));
    }

    public WishlistDTO update(@NotNull @Positive Long id , @Valid WishlistDTO wishlist){
        return this.wishlistRepository.findById(id)
                .map(record -> {
                    record.setItem(wishlist.item());
                    record.setLink(wishlist.link());
                    return wishlistRepository.save(record);
                }).map(this.wishlistMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
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
