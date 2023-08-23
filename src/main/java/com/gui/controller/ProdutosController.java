package com.gui.controller;

import com.gui.domain.dto.DadosProduto;
import com.gui.domain.service.ProdutoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Validated
@RequestMapping("/produtos")
@CrossOrigin
//@SecurityRequirement(name = "bearer-key")
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DadosProduto salvar(@RequestPart MultipartFile file, @ModelAttribute DadosProduto dadosProduto) {
        return produtoService.salvarProduto(file, dadosProduto);

    }

    @GetMapping("/busca")
    public Page<DadosProduto> busca(@RequestParam("busca") String buscado,
                                    @PageableDefault(sort = "descricao", direction = Sort.Direction.ASC, size = 8) Pageable paginacao) {
        return produtoService.buscador(buscado, paginacao);
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<DadosProduto>> obterTodos(
            @PageableDefault(sort = "descricao", size = 10) Pageable pageable) {
        Page<DadosProduto> pageProdutos = produtoService.obterTodos(pageable);
        if (pageProdutos != null && !pageProdutos.isEmpty()) {
            return ResponseEntity.ok().body(pageProdutos);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<DadosProduto> obterUltimosLancamentos(@PageableDefault(sort = "descricao", direction = Sort.Direction.ASC, size = 8) Pageable paginacao ) {
            return produtoService.obterUltimosLançamentos(paginacao);
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
