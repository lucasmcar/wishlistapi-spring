package com.codeexpertssistemas.wishlist.dto;

import com.codeexpertssistemas.wishlist.enums.Status;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record WishlistDTO (
        Long wishListId,
        @NotNull @Length(max = 100) String item,
        @NotNull @Length(max = 160)String link,
        String status

){

    /*private Long wishListId;

    @NotNull
    @Length(max = 100)
    private String item;

    @NotNull
    @Length(max = 160)
    private String link;

    @NotNull
    private String status = "Ativo";*/
}
