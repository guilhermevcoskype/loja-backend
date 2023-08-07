package com.gui.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gui.domain.dto.UsuarioRecord;
import com.gui.domain.repository.UsuarioRepository;
import com.gui.infra.mapper.UsuarioMapper;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioMapper usuarioMapper;

	public void salvarUsuario(UsuarioRecord usuarioDTO) {
		usuarioRepository.save(usuarioMapper.mapperToUsuarioToSave(usuarioDTO));
	}

	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		return usuarioRepository.findByLogin(nome);
	}

	/* public Usuario acharPorId(Long id) {
		return usuarioRepository.findById(id).map(usuario -> usuario)
				.orElseThrow(() -> new UsuarioException(id));
	} */
}
