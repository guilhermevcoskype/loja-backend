package com.gui.domain.model;

import com.gui.domain.dto.DadosProduto;
import com.gui.domain.service.ProdutoService;
import com.gui.domain.utils.FormatMoeda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CarrinhoCompras implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();

    @Autowired
    private ProdutoService produtoService;

    public void add(CarrinhoItem item) {
        itens.put(item, getQuantidade(item) + 1);
    }

    public int getQuantidade(CarrinhoItem item) {
        if (!itens.containsKey(item)) { //esse método usa o método equals e hashcode do método objet, então é necessário sobrescrevê-los nos CarrinhoItem e Produto, igual faz com get e set, procurados para comparar direito
            itens.put(item, 0);
        }
        return itens.get(item);
    }

    public void aumentaQuantidade(Long codigo) {
        DadosProduto dadosProduto = produtoService.buscarProdutoPorCodigo(codigo);
        CarrinhoItem item = new CarrinhoItem(dadosProduto);
        if (itens.get(item) < 10)
            this.add(item);
    }

    public void reduzQuantidade(Long codigo) {
        DadosProduto dadosProduto = produtoService.buscarProdutoPorCodigo(codigo);
        CarrinhoItem item = new CarrinhoItem(dadosProduto);
        if (itens.get(item) > 1)
            itens.put(item, itens.get(item) - 1);
    }

    public void remover(Long codigo) {
        DadosProduto dadosProduto = produtoService.buscarProdutoPorCodigo(codigo);
        itens.remove(new CarrinhoItem(dadosProduto));
    }

    public void removerTodos() {
        itens.clear();
    }

    public int getQuantidade() { // criado pra retornar a pagina o numero de itens existentes no carrinho
        return itens.values().stream().reduce(0, (proximo, acumulador) -> (proximo + acumulador));
    }

    public Collection<CarrinhoItem> getItens() {
        return itens.keySet();
    }

    public BigDecimal getTotal(CarrinhoItem item) {
        return item.getTotal(getQuantidade(item));
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CarrinhoItem item : itens.keySet()) {
            total = total.add(getTotal(item));
        }
        return total;
    }

    public String getTotalFormatado() {
        return FormatMoeda.doubleParaString(getTotal().doubleValue());
    }
}










