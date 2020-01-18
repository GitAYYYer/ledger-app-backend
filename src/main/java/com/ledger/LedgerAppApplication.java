package com.ledger;

import com.ledger.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
Not sure if the @EnableJpaRepo is needed.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class LedgerAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LedgerAppApplication.class, args);
	}
}
