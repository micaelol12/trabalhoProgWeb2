package com.mlconti.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mlconti.demo.domain.Categoria;
import com.mlconti.demo.repository.CategoriaRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
			CategoriaRepository categoriaRepository) {
		return (args) -> {


			Categoria cate1 = new Categoria(null,"esporte");
			Categoria cate2 = new Categoria(null,"lazer");

			categoriaRepository.save(cate1);
			categoriaRepository.save(cate2);
		};
	}
}
