package com.gui.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosUsuario(
    Long id, 
    @NotBlank @NotNull String nome, 
    @NotBlank @NotNull String senha, 
    String roles)
{
	
}
