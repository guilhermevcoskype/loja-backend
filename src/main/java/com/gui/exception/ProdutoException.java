package com.gui.exception;

public class ProdutoException extends RuntimeException{


    public ProdutoException (Long id){
        super("Produto não encontrado com id: " + id);
    }
    
}
