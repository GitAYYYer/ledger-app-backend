package com.ledger.backend;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LedgerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LedgerAppApplication.class, args);
	}

	@Bean
	public void run() {
		Main main = new Main();		

		System.out.println("Enter mode: (1 or 2)");
		Scanner sc = new Scanner(System.in);
		String mode = sc.nextLine();
		if(mode.equals("1")) {
			main.menu();
		}else {
			main.maidSplit();
		}
		main.start();
	}
}
