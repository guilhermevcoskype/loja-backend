package com.gui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gui.domain.dto.DadosUsuario;
import com.gui.domain.service.UsuarioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<DadosUsuario> obterUsuarios() {
		return usuarioService.obterUsuarios();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarUsuario(@RequestBody @Valid DadosUsuario dadosUsuario) {
		usuarioService.salvarUsuario(dadosUsuario);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerUsuario(@PathVariable("id") @NotNull @Positive Long id) {
		usuarioService.removerUsuario(id);
	}

}