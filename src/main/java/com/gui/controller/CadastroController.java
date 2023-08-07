package com.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gui.domain.dto.ProdutoDTO;
import com.gui.domain.dto.UsuarioRecord;
import com.gui.domain.service.ProdutoService;
import com.gui.domain.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
// @RequestScope
@RequestMapping("/cadastro")

public class CadastroController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO salvar(MultipartFile file, @Valid @RequestBody ProdutoDTO produtoDTO) {

		return produtoService.salvarProduto(file, produtoDTO);

	}

	@PostMapping
	@RequestMapping("/sm")
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO salvarSemFile(@Valid @RequestBody ProdutoDTO produtoDTO) {

		return produtoService.salvarProdutoSemFile(produtoDTO);
	}

	@RequestMapping(value = "/usuario", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarUsuario(@RequestBody @Valid UsuarioRecord usuarioDTO) {
		usuarioService.salvarUsuario(usuarioDTO);
	}

}