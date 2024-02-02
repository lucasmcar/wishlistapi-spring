package com.codeexpertssistemas.wishlist.dto.mapper;

import com.codeexpertssistemas.wishlist.dto.WishlistDTO;
import com.codeexpertssistemas.wishlist.enums.Status;
import com.codeexpertssistemas.wishlist.model.Wishlist;
import org.springframework.stereotype.Component;

import static com.codeexpertssistemas.wishlist.enums.Status.*;

@Component
public class WishlistMapper {

    public WishlistDTO toDTO(Wishlist wishlist){
        if(wishlist == null){
            return null;
        }
        return new WishlistDTO(
                wishlist.getWishListId(),
                wishlist.getItem(),
                wishlist.getLink(),
                wishlist.getStatus().getValue());
    }

    public Wishlist toEntity(WishlistDTO wishlistDTO){

        if(wishlistDTO == null){
            return null;
        }
        Wishlist wishlist = new Wishlist();
        if(wishlistDTO.wishListId() != null){
            wishlist.setWishListId(wishlistDTO.wishListId());
        }
        wishlist.setLink(wishlistDTO.link());
        wishlist.setItem(wishlistDTO.item());
        return wishlist;
    }

    public Status convertStatusValue(String value){
        if(value == null){
            return null;
        }

        return switch (value){
            case "Ativo" -> Status.ATIVO;
            case "Inativo" -> Status.INATIVO;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

}
