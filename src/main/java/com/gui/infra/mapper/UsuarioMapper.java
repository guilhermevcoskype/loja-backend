package com.gui.infra.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gui.domain.dto.UsuarioRecord;
import com.gui.domain.model.Role;
import com.gui.domain.model.Usuario;

import java.util.Set;

@Component
public class UsuarioMapper {

    @Autowired
	private PasswordEncoder passwordEncoder;

    public Usuario mapperToUsuarioToSave(UsuarioRecord usuarioRecord) {
        if (usuarioRecord != null) {
            return new Usuario(usuarioRecord.id(), usuarioRecord.nome(), passwordEncoder.encode(usuarioRecord.senha()), Set.of(Role.valueOf(usuarioRecord.roles())));
        }
        return null;
    }

}
