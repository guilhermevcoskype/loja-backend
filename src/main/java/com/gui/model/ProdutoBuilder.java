package com.gui.model;
import java.math.BigDecimal;
import java.sql.Date;

public class ProdutoBuilder {

	private String descricao;
	private Long estoque;
	private BigDecimal preco;
	private Date dataInsercao;
	private String urlImagem;
	private TipoProduto tipoProduto;

	public Produto build() {
		return new Produto(descricao, estoque, preco, urlImagem, tipoProduto, dataInsercao);
	}

	public ProdutoBuilder comDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public ProdutoBuilder comEstoque(Long estoque) {
		this.estoque = estoque;
		return this;
	}

	public ProdutoBuilder comPreco(BigDecimal preco) {
		this.preco = preco;
		return this;
	}

	public ProdutoBuilder comDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
		return this;
	}

	public ProdutoBuilder comUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
		return this;
	}

	public ProdutoBuilder comTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
		return this;
	}

}