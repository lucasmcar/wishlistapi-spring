package com.codeexpertssistemas.wishlist.model;



import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idWish;
    private String item;
    private String link;


}