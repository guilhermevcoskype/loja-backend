package com.gui.model;

public enum TipoProduto {
    CAMISA("CAMISA"), LIVRO("LIVRO"), GERAL("GERAL");
	
	private final String tipo;
	
	TipoProduto(String tipo){
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
}