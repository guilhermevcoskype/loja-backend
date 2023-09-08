package com.gui.controller;

import com.gui.domain.model.CarrinhoCompras;
import com.gui.domain.model.DadosPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/pagamento")
@CrossOrigin
public class PagamentoController {

    @Autowired
    RestTemplate restTemplate;

//    final static String URL_WEBSERVICE = "http://localhost:8081/pagamento";

    final static String URL_WEBSERVICE =
            "http://book-payment.herokuapp.com/payment";

    @PostMapping
    public Callable<ResponseEntity<String>> finalizar(@RequestParam Double pagamento) {
        return () -> {
            String response = restTemplate.postForObject(URL_WEBSERVICE, new DadosPagamento(BigDecimal.valueOf(pagamento)),
                    String.class);
            return ResponseEntity.ok(response);
        };
    }

}