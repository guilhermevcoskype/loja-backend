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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gui.domain.dto.DadosProduto;
import com.gui.domain.service.ProdutoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/produtos")
@SecurityRequirement(name = "bearer-key")
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DadosProduto salvar(MultipartFile file, @Valid @RequestBody DadosProduto dadosProduto) {

        return produtoService.salvarProduto(file, dadosProduto);

    }

    @PostMapping
    @RequestMapping("/sm")
    @ResponseStatus(HttpStatus.CREATED)
    public DadosProduto salvarSemFile(@Valid @RequestBody DadosProduto dadosProduto) {

        return produtoService.salvarProdutoSemFile(dadosProduto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosProduto>> obterTodos(
            @PageableDefault(sort = "descricao", size = 2) Pageable pageable) {
        Page<DadosProduto> pageProdutos = produtoService.obterTodos(pageable);
        if (pageProdutos != null && !pageProdutos.isEmpty()) {
            return ResponseEntity.ok().body(pageProdutos);
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public DadosProduto buscarProdutoPorCodigo(@PathVariable("id") @NotNull @Positive Long id) {
        return produtoService.buscarProdutoPorCodigo(id);

    }

    @PutMapping("/{id}")
    public DadosProduto editarProduto(@PathVariable("id") @NotNull @Positive Long id,
            @RequestBody @Valid DadosProduto produto) {
        return produtoService.editarProduto(id, produto);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerProduto(@PathVariable("id") @NotNull @Positive Long id) {
        produtoService.removerProduto(id);
    }
}
