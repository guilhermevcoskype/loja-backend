package com.gui.controller;

import com.gui.domain.model.CarrinhoCompras;
import com.gui.domain.model.DadosPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.Callable;

@Controller
@RequestMapping("/pagamento")

public class PagamentoController {

    @Autowired
    private CarrinhoCompras carrinho;

    @Autowired
    RestTemplate restTemplate;

    final static String URL_WEBSERVICE = "http://localhost:8081/pagamento";

    /*
     * final static String URL_WEBSERVICE =
     * "http://book-payment.herokuapp.com/payment";
     */

    @PostMapping
    public Callable<ModelAndView> finalizar(RedirectAttributes model) {
        return () -> {
            try {
                String response = restTemplate.postForObject(URL_WEBSERVICE, new DadosPagamento(carrinho.getTotal()),
                        String.class);
                model.addFlashAttribute("message", response);
                carrinho.removerTodos();
                return new ModelAndView("redirect:/finalizarPage");
            } catch (HttpClientErrorException e) {
                e.printStackTrace();
                model.addFlashAttribute("falha", "Ocorreu uma falha na transação, favor contatar seu banco");
                return new ModelAndView("redirect:/errorPage");
            }
        };
    }

}