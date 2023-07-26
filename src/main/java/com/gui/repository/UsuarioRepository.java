package com.gui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gui.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
