package com.gui.domain.model;

import java.math.BigDecimal;
import java.sql.Date;

import com.gui.domain.conversor.TipoProdutoConversor;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;

	@NotBlank
	@NotNull
	private String descricao;

	@NotNull
	private Long estoque;

	@NotNull
	private BigDecimal preco;
	
	@NotNull
	private Date dataInsercao;

	
	@NotBlank
	@NotNull
	private String urlImagem;

	@NotNull
	@Convert(converter = TipoProdutoConversor.class)
	private TipoProduto tipoProduto;

}
