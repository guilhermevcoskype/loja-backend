package com.gui.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull/* 
    @NotBlank
    @Length(min = 3, max = 200) */
    @Column(name="nome", length = 200)
    private String nome;

    @NotNull/* 
    @NotBlank
    @Digits(integer = 4, fraction = 0) */
    @Column(name="quantidade")
    private long quantidade;
    
}
