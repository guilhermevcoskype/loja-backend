package com.gui.model;

import java.math.BigDecimal;
import java.sql.Date;

import com.gui.service.FormatMoeda;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Produto {

	@Id @GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long codigo;
	private String descricao;
	private Long estoque;
	private BigDecimal preco;
	
	private Date dataInsercao;

	
	private String urlImagem;
	@Enumerated(EnumType.STRING)
	private TipoProduto tipoProduto;
	
	public Produto() {
	}
	
	public Produto(String descricao, Long estoque, BigDecimal preco, String urlImagem, TipoProduto tipoProduto, Date dataInsercao) {
		this.descricao = descricao;
		this.estoque = estoque;
		this.preco = preco;
		this.urlImagem = urlImagem;
		this.tipoProduto = tipoProduto;
		this.dataInsercao = dataInsercao;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getEstoque() {
		return estoque;
	}
	public void setEstoque(Long estoque) {
		this.estoque = estoque;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public String getPrecoFormatado() {
		return FormatMoeda.FORMATAR(preco.doubleValue());
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public String getUrlImagem() {
		return urlImagem;
	}
	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}
	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}	
	public Date getDataInsercao() {
		return dataInsercao;
	}	
	public void setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", estoque=" + estoque + ", preco=" + preco
				+ ", dataInsercao=" + dataInsercao + ", urlImagem=" + urlImagem + ", tipoProduto=" + tipoProduto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
