package com.gui.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gui.model.Produto;
import com.gui.repository.ProdutoRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutosController {

    private final ProdutoRepository produtosRepository;
    
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtosRepository.findAll();
    }
}
