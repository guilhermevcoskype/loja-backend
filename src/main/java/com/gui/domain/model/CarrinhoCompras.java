/* package com.gui.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.gui.infra.utils.FormatMoeda;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)

public class CarrinhoCompras implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public void add(CarrinhoItem item){
	    itens.put(item, getQuantidade(item) + 1);
	}
	
	public int getQuantidade(CarrinhoItem item) {
	    if(!itens.containsKey(item)){ //esse método usa o método equals e hashcode do método objet, então é necessário sobrescrevê-los nos CarrinhoItem e Produto, igual faz com get e set, procurados para comparar direito
	        itens.put(item, 0);
	    }
	    return itens.get(item);
	}
	
	public void aumentaQuantidade(Long codigo) {
		Produto produto = new Produto();
	    produto.setCodigo(codigo);
	    CarrinhoItem item = new CarrinhoItem(produto);
	   if ( itens.get(item) < 10)
	    this.add(item);
	}
	
	public void reduzQuantidade(Long codigo) {
		Produto produto = new Produto();
	    produto.setCodigo(codigo);
	    CarrinhoItem item = new CarrinhoItem(produto);
		 if ( itens.get(item) > 1)
			 itens.put(item, itens.get(item) - 1);
	}
	
	public void remover(Long codigo) {
	    Produto produto = new Produto();
	    produto.setCodigo(codigo);
	    itens.remove(new CarrinhoItem(produto));
	}
	
	public void removerTodos() { 
	    itens.clear();
	}
	
	public int getQuantidade(){ // criado pra retornar � p�gina o n�mero de itens existentes no carrinho
	    return itens.values().stream().reduce(0, (proximo, acumulador) -> (proximo + acumulador));
	}
	
	public Collection<CarrinhoItem> getItens() {
	    return itens.keySet();
	}
	
	public BigDecimal getTotal(CarrinhoItem item){
	    return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal(){
	    BigDecimal total = BigDecimal.ZERO;
	    for (CarrinhoItem item : itens.keySet()) {
	        total = total.add(getTotal(item));
	    }
	    return total;
	}

	public String getTotalFormatado(){
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return FormatMoeda.doubleParaString(total.doubleValue());
	}
}










 */