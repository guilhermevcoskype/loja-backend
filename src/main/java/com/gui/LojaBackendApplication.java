package com.gui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
// @EnableCaching

public class LojaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LojaBackendApplication.class, args);
    }
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
