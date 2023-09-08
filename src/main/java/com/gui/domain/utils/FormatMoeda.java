package com.gui.domain.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class FormatMoeda {

	public static String doubleParaString (Double numero) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		formatter.setMaximumFractionDigits(2);
		return formatter.format(numero);		
	}

	public static BigDecimal stringParaBigdecimal(String preco) {
		BigDecimal precoBigDecimal = null;
		
		preco = preco.replace("." , ",");
		precoBigDecimal = BigDecimal.valueOf(Long.parseLong(preco)).setScale(2, RoundingMode.HALF_EVEN);
		return precoBigDecimal;
	}
}
