package com.gui.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gui.dto.ProdutoDTO;
import com.gui.service.ProdutoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/produtos")
public class ProdutosController {

    private final ProdutoService produtoService;

    public ProdutosController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /*
     * @GetMapping
     * public ResponseEntity<List<Produto>> listarProdutos() {
     * List<Produto> ultimosLancamentos = produtoService.obterUltimosLançamentos();
     * if (ultimosLancamentos != null) {
     * return ResponseEntity.ok().body(ultimosLancamentos);
     * } else
     * return ResponseEntity.notFound().build();
     * }
     */

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> obterTodos() {
        List<ProdutoDTO> listProdutos = produtoService.obterTodos();
        if (listProdutos != null && !listProdutos.isEmpty()) {
            return ResponseEntity.ok().body(listProdutos);
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ProdutoDTO buscarProdutoPorCodigo(@PathVariable("id") @NotNull @Positive Long codigo) {
        return produtoService.buscarProdutoPorCodigo(codigo);

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    // @ResponseStatus(HttpStatus.CREATED) pode usar como está ou com essa linha,
    // tirando o ResponseEntity no retorno e retornar apenas o objeto.
    public ProdutoDTO salvarProduto(@RequestBody @Valid ProdutoDTO produto) {

        return produtoService.salvarProduto(produto);
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
