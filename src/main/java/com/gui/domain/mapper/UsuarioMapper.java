package com.gui.domain.mapper;

import com.gui.domain.dto.DadosUsuarioDTO;
import com.gui.domain.model.Role;
import com.gui.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    @Autowired
	private PasswordEncoder passwordEncoder;

    public Usuario mapperToUsuario(DadosUsuarioDTO dadosUsuario) {
        if (dadosUsuario != null) {
            return new Usuario(dadosUsuario.id(), dadosUsuario.nome(), passwordEncoder.encode(dadosUsuario.senha()), Role.valueOf(dadosUsuario.roles()));
        }
        return null;
    }
    
    public DadosUsuarioDTO mapperToRecord(Usuario usuario) {
        if (usuario != null) {
            return new DadosUsuarioDTO(usuario.getId(), usuario.getLogin(), usuario.getSenha(), usuario.getRole().toString());
        }
        return null;
    }

}
