package com.ledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class LedgerAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LedgerAppApplication.class, args);
	}
}
