package com.gui.controller;

import com.gui.domain.dto.DadosProduto;
import com.gui.domain.model.CarrinhoCompras;
import com.gui.domain.model.CarrinhoItem;
import com.gui.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CarrinhoCompras carrinhoCompras;

    private CarrinhoItem criaItem(Long codigo) {
        DadosProduto dadosProduto = this.produtoService.buscarProdutoPorCodigo(codigo);
        CarrinhoItem carrinhoItem = new CarrinhoItem(dadosProduto);
        return carrinhoItem;
    }

    @GetMapping
    public CarrinhoCompras itens(ModelAndView modelAndView) {
        return carrinhoCompras;
    }

    @RequestMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public CarrinhoCompras add(Long codigo) {
        CarrinhoItem carrinhoItem = criaItem(codigo);
        carrinhoCompras.add(carrinhoItem);
        return carrinhoCompras;
    }

    @RequestMapping("/remover")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CarrinhoCompras remover(Long codigo) {
        carrinhoCompras.remover(codigo);
        return carrinhoCompras;
    }

    @RequestMapping("/diminuir")
    @ResponseStatus(HttpStatus.OK)
    public CarrinhoCompras diminuir(Long codigo) {
        carrinhoCompras.reduzQuantidade(codigo);
        return carrinhoCompras;
    }

    @RequestMapping("/aumentar")
    @ResponseStatus(HttpStatus.OK)
    public CarrinhoCompras aumentar(Long codigo) {
        carrinhoCompras.aumentaQuantidade(codigo);
        return carrinhoCompras;
    }

}
