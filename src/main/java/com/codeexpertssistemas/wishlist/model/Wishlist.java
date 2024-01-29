package com.codeexpertssistemas.wishlist.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long wishListId;

    @NotNull
    @Size(min = 10, max = 100)
    private String item;

    @NotNull
    @Size(min = 10, max = 160)
    private String link;


}
