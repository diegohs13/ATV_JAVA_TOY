package com.example.lojaDeBrinquedos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LojaDeBrinquedosApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaDeBrinquedosApplication.class, args);
		System.out.println("Spring boot rodando na porta 8080");
	}

}
