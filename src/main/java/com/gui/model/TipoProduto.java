package com.gui.model;

public enum TipoProduto {
    CAMISA("camisa"), LIVRO("livro"), GERAL("geral");
	
	private final String tipo;
	
	TipoProduto(String tipo){
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
}