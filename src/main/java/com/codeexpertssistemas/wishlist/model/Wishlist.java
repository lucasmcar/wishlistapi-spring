package com.codeexpertssistemas.wishlist.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@SQLDelete(sql = "UPDATE wishlist SET status = 'Inativo' where wish_list_id = ?")
@Where(clause = "status = 'Ativo'")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long wishListId;

    @NotNull
    @Length(max = 100)
    @Column(length = 10, nullable = false)
    private String item;

    @NotNull
    @Length(max = 160)
    @Column(length = 10, nullable = false)
    private String link;

    @NotNull
    @Column(length = 10, nullable = false)
    @Pattern(regexp = "Ativo|Inativo")
    private String status = "Ativo";

}
