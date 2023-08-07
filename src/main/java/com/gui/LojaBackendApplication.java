package com.gui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
// @EnableCaching

public class LojaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaBackendApplication.class, args);
	}
}
