package com.gui.service;

import java.text.NumberFormat;

public class FormatMoeda {

	public static String FORMATAR (Double numero) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		formatter.setMaximumFractionDigits(2);
		return formatter.format(numero);		
	}
}
