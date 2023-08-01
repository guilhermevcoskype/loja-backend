package com.gui.dto;

import java.math.BigDecimal;
import java.sql.Date;

import com.gui.model.TipoProduto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(
        @NotNull Long codigo,

        @NotBlank @NotNull String descricao,

        @NotNull Long estoque,

        @NotNull BigDecimal preco,

        @NotNull Date dataInsercao,

        @NotBlank @NotNull String urlImagem,

        @NotNull TipoProduto tipoProduto

) {

}
