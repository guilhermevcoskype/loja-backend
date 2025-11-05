package com.gui.controller;

import com.gui.domain.dto.DadosEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastrarEmail")
@CrossOrigin
public class EmailProducerController {

    @Autowired
    private KafkaTemplate<String, DadosEmailDTO> kafkaTemplate;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void emailProducer(@RequestBody DadosEmailDTO dadosEmail) {
        kafkaTemplate.send("email-topic", dadosEmail);
    }
}
