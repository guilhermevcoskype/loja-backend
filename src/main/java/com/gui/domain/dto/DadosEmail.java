package com.gui.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosEmail (

        @NotBlank @NotNull String email,
        @NotBlank @NotNull String idProduto

) {

}