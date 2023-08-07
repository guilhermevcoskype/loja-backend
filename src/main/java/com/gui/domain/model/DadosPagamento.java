package com.gui.domain.model;

import java.math.BigDecimal;

public class DadosPagamento {
	
	private BigDecimal value;

	public DadosPagamento(BigDecimal value) {
		this.value = value;
	}

	public DadosPagamento() {
	}

	public BigDecimal getValue() {
		return value;
	}
}
