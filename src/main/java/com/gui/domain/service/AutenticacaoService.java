/*

package com.gui.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gui.domain.model.Usuario;
import com.gui.domain.repository.UsuarioRepository;


@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		return repository.findByLogin(nome);
	}

	public void salvar(Usuario usuario) {
		repository.save(usuario);
	}
}
*/
