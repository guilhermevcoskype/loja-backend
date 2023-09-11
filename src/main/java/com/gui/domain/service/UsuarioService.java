package com.gui.domain.service;

import java.util.List;

import com.gui.infra.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gui.domain.dto.DadosUsuario;
import com.gui.domain.mapper.UsuarioMapper;
import com.gui.domain.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public void salvarUsuario(DadosUsuario dadosUsuario) {
        if (usuarioRepository.findByLogin(dadosUsuario.nome()) == null) {
            usuarioRepository.save(usuarioMapper.mapperToUsuario(dadosUsuario));
        } else {
            throw new UsuarioException();
        }

    }

    public void removerUsuario(Long id) {

        usuarioRepository.delete(usuarioRepository.findById(id).get());
    }

    public List<DadosUsuario> obterUsuarios() {

        return usuarioRepository.findAll().stream().map(usuarioMapper::mapperToRecord).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {

        return usuarioRepository.findByLogin(nome);
    }
}
