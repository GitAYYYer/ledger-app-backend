package com.ledger.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ledger.model.Pool;

@SpringBootApplication
public class LedgerAppApplication {
//	Controller main = new Controller();
//	Pool pool = new Pool(5);
	
	public static void main(String[] args) {
		SpringApplication.run(LedgerAppApplication.class, args);
	}
}
