package com.gui.model;
import java.io.Serializable;
import java.math.BigDecimal;

import com.gui.service.FormatMoeda;


public class CarrinhoItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Produto produto;

	public BigDecimal getPreco(){
	    return produto.getPreco();
	}
	
	public BigDecimal getTotal(int quantidade) {
	    return this.getPreco().multiply(new BigDecimal(quantidade));
	}

	public String getTotalFormatado(int quantidade) {
		return FormatMoeda.FORMATAR(this.getPreco().multiply(new BigDecimal(quantidade)).doubleValue());
	}
	
	public CarrinhoItem(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	//metodos gerados com todos os itens, diferente do produto que deve ser apenas com o codigo.

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		CarrinhoItem other = (CarrinhoItem) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

	
}
