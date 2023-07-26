package com.gui.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import com.gui.model.TipoProduto;


public class Conversor {

	public static TipoProduto retornaTipoProdutoSelecionado (String tipoProduto) {

		if (tipoProduto.equals("camisa")) {
			return TipoProduto.CAMISA;
		} else if (tipoProduto.equals("livro")) {
			return TipoProduto.LIVRO;
		} else
			return TipoProduto.GERAL;
	}
	
	public static BigDecimal stringParaBigdecimal(String preco) {
		BigDecimal precoBigDecimal = null;
		
		preco = preco.replace("." , ",");
		precoBigDecimal = new BigDecimal(stringParaDouble(preco)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		return precoBigDecimal;
	}

	private static double stringParaDouble (String preco)  {
		
		try {
			return new DecimalFormat().parse(preco).doubleValue();
		} catch (ParseException e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}
}