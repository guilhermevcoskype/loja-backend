package com.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gui.domain.dto.ProdutoDTO;
import com.gui.domain.service.ProdutoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> obterTodos(
            @PageableDefault(sort = "descricao", size = 2) Pageable pageable) {
        Page<ProdutoDTO> pageProdutos = produtoService.obterTodos(pageable);
        if (pageProdutos != null && !pageProdutos.isEmpty()) {
            return ResponseEntity.ok().body(pageProdutos);
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ProdutoDTO buscarProdutoPorCodigo(@PathVariable("id") @NotNull @Positive Long codigo) {
        return produtoService.buscarProdutoPorCodigo(codigo);

    }

    @PutMapping("/{id}")
    public ProdutoDTO editarProduto(@PathVariable("id") @NotNull @Positive Long codigo,
            @RequestBody @Valid ProdutoDTO produto) {
        return produtoService.editarProduto(codigo, produto);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerProduto(@PathVariable("id") @NotNull @Positive Long codigo) {
        produtoService.removerProduto(codigo);
    }
}
