package com.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gui.domain.dto.DadosProduto;
import com.gui.domain.service.ProdutoService;

@RestController
@RequestMapping("/buscador")
public class BuscadorController {

    @Autowired
    private ProdutoService service;

    @GetMapping()
    public Page<DadosProduto> busca(@RequestParam("busca") String buscado,
            @PageableDefault(sort = "descricao", direction = Direction.ASC, page = 0, size = 15) Pageable paginacao) {
        return service.buscador(buscado, paginacao);
    }

}