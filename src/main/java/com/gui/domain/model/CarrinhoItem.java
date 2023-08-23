package com.gui.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.gui.domain.dto.DadosProduto;
import com.gui.domain.utils.FormatMoeda;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CarrinhoItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private DadosProduto produto;

    public BigDecimal getPreco() {
        return new BigDecimal(produto.preco());
    }

    public CarrinhoItem(DadosProduto produto) {
        this.produto = produto;
    }

    public BigDecimal getTotal(int quantidade) {
        return this.getPreco().multiply(new BigDecimal(quantidade));
    }

    public String getTotalFormatado(int quantidade) {
        return FormatMoeda.doubleParaString(this.getPreco().multiply(new BigDecimal(quantidade)).doubleValue());
    }
}
