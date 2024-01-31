package com.codeexpertssistemas.wishlist.exception;

public class RecordNotFoundException  extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public RecordNotFoundException(Long id){
        super("Lista de desejos não encontrado com id" + id);
    }

}
