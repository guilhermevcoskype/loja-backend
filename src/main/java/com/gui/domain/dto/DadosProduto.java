package com.gui.domain.dto;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosProduto(

                Long id,

                @NotBlank @NotNull String descricao,

                @NotNull Long estoque,

                @NotNull BigDecimal preco,

                @NotNull Date dataInsercao,

                @NotBlank @NotNull String urlImagem,

                @NotNull String tipoProduto

) {

}
