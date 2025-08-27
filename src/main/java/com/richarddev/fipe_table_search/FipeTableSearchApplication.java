package com.richarddev.fipe_table_search;

import com.richarddev.fipe_table_search.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class FipeTableSearchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipeTableSearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
