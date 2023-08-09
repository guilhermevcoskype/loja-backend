package com.gui.domain.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gui.domain.dto.DadosUsuario;
import com.gui.domain.model.Role;
import com.gui.domain.model.Usuario;

import java.util.Set;

@Component
public class UsuarioMapper {

    @Autowired
	private PasswordEncoder passwordEncoder;

    public Usuario mapperToUsuario(DadosUsuario dadosUsuario) {
        if (dadosUsuario != null) {
            return new Usuario(dadosUsuario.id(), dadosUsuario.nome(), passwordEncoder.encode(dadosUsuario.senha()), Set.of(Role.valueOf(dadosUsuario.roles())));
        }
        return null;
    }
    
    public DadosUsuario mapperToRecord(Usuario usuario) {
        if (usuario != null) {
            return new DadosUsuario(usuario.getId(), usuario.getLogin(), usuario.getSenha(), usuario.getRoles().toString());
        }
        return null;
    }

}
