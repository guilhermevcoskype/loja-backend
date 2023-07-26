package com.gui;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// @SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableCaching

public class LojaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaBackendApplication.class, args);
	}

	@Bean //Código para evitar erro de permissão cors no site
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "<other urls>")); // Collections.singletonList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token",
				"Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

/* 	@Bean
	CommandLineRunner initDatabase(ProdutosRepository produtosRepository) {

		return args -> {
			Produto produtos = new Produto();
			produtos.setId(1);
			produtos.setNome("Camisa top");
			produtos.setQuantidade(3);
			produtosRepository.save(produtos);

			Produto produtos2 = new Produto();
			produtos2.setId(2);
			produtos2.setNome("Camisa meio mé");
			produtos2.setQuantidade(200);
			produtosRepository.save(produtos2);

			Produto produtos3 = new Produto();
			produtos3.setId(3);
			produtos3.setNome("Camisa paia");
			produtos3.setQuantidade(25);
			produtosRepository.save(produtos3);
		};
	} */
}
