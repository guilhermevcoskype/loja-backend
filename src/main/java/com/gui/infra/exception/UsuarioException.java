package com.gui.infra.exception;

public class UsuarioException extends RuntimeException{


    public UsuarioException (Long id){
        super("Usuário não encontrado com id: " + id);
    }

    public UsuarioException (){
        super("Usuário já existente");
    }

}