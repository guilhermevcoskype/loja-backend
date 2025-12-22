package com.gui.controller;

import com.gui.domain.dto.DadosProdutoDTO;
import com.gui.domain.model.TipoProduto;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Validated
@RequestMapping("/produtos")
@CrossOrigin
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosProdutoDTO> salvar(@RequestPart MultipartFile file, @ModelAttribute DadosProdutoDTO dadosProduto, UriComponentsBuilder uriBuilder) {
        var produtoSalvo = produtoService.salvarProduto(file, dadosProduto);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoSalvo.id()).toUri();
        return ResponseEntity.created(uri).body(produtoSalvo);

    }

    @GetMapping("/busca")
    public ResponseEntity<Page<DadosProdutoDTO>> busca(@RequestParam("busca") String buscado,
                                                       @PageableDefault(sort = "descricao", direction = Sort.Direction.ASC, size = 8) Pageable paginacao) {
        return ResponseEntity.ok(produtoService.buscador(buscado, paginacao));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<DadosProdutoDTO>> obterTodos(
            @PageableDefault(sort = "descricao", size = 10) Pageable pageable) {
        Page<DadosProdutoDTO> pageProdutos = produtoService.obterTodos(pageable);
        if (pageProdutos != null && !pageProdutos.isEmpty()) {
            return ResponseEntity.ok().body(pageProdutos);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosProdutoDTO>> obterUltimosLancamentos(@PageableDefault(sort = "dataInsercao", size = 8) Pageable paginacao) {
        Page<DadosProdutoDTO> pageProdutos = produtoService.obterUltimosLançamentos(paginacao);
        if (pageProdutos != null && !pageProdutos.isEmpty()) {
            return ResponseEntity.ok().body(pageProdutos);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosProdutoDTO> detalharProduto(@PathVariable("id") @NotNull @Positive Long id) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorCodigo(id));

    }

    @GetMapping("/tipoProduto")
    public ResponseEntity<TipoProduto[]> obterTipoProduto() {
        return ResponseEntity.ok(TipoProduto.values());

    }

    @GetMapping("/buscarProdutoPorTipo")
    public ResponseEntity<Page<DadosProdutoDTO>> obterProdutosPorTipo(@RequestParam("tipoProduto") String buscado,
                                                                      @PageableDefault(sort = "descricao", direction = Sort.Direction.ASC, size = 8) Pageable paginacao) {
        return ResponseEntity.ok(produtoService.obterPorTipo(TipoProduto.valueOf(buscado), paginacao));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosProdutoDTO> editarProduto(@PathVariable("id") @NotNull @Positive Long id,
                                                         @RequestBody @Valid DadosProdutoDTO produto) {
        return ResponseEntity.ok(produtoService.editarProduto(id, produto));

    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerProduto(@PathVariable("id") @NotNull @Positive Long id) {

        produtoService.removerProduto(id);
    }
}
