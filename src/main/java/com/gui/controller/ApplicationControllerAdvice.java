package com.gui.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gui.exception.ProdutoException;

import jakarta.validation.ValidationException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(ProdutoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerProdutoException(ProdutoException ex) {
        return ex.getMessage();
    }
    
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String handlerConstraintViolationException(ValidationException ex) {
        return ex.getMessage();
    }
}
