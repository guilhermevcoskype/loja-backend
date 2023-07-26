package com.gui.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	@Id
    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 22)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	@Override
	public String getAuthority() {
		return nome;
	}


}