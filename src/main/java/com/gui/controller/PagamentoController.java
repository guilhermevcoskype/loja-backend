package com.gui.controller;

import com.gui.domain.model.DadosPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public Callable<ResponseEntity<String>> finalizar(@RequestBody DadosPagamento pagamento) {
        System.out.println("passou aqui");

        return () -> {
            String response = restTemplate.postForObject(URL_WEBSERVICE, new DadosPagamento(pagamento.getValue()),
                    String.class);
            return ResponseEntity.ok(response);
        };
    }

}