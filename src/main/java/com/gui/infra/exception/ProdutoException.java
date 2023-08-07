package com.gui.infra.exception;

public class ProdutoException extends RuntimeException{


    public ProdutoException (Long id){
        super("Produto não encontrado com id: " + id);
    }
    
}
