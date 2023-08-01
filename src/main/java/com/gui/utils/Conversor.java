package com.gui.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class Conversor {

	private Conversor(){
		
	}

	/* public static TipoProduto retornaTipoProdutoSelecionado (String tipoProduto) {

		if (tipoProduto.equals("camisa")) {
			return TipoProduto.CAMISA;
		} else if (tipoProduto.equals("livro")) {
			return TipoProduto.LIVRO;
		} else
			return TipoProduto.GERAL;
	} */
	
	public static BigDecimal stringParaBigdecimal(String preco) {
		BigDecimal precoBigDecimal = null;
		
		preco = preco.replace("." , ",");
		precoBigDecimal = BigDecimal.valueOf(Long.parseLong(preco)).setScale(2, RoundingMode.HALF_EVEN);
		return precoBigDecimal;
	}

	/* private static double stringParaDouble (String preco)  {
		
		try {
			return new DecimalFormat().parse(preco).doubleValue();
		} catch (ParseException e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
	} */
}