package com.gui.domain.model;

public enum Role {

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");
	
	private final String role;
	
	Role(String role){
        this.role = role;
    }
    public String getRole() {
        return role;
    }

}