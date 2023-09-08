package com.gui.domain.dto;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosProduto(

                String id,

                @NotBlank @NotNull String descricao,

                @NotNull String estoque,

                @NotNull String preco,

                @NotNull String dataInsercao,

                String urlImagem,

                @NotNull String tipoProduto

) {

}
