package it.cs.unicam.ids.filiera.demo;

import it.cs.unicam.ids.filiera.controllers.SellerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.Scanner;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FilieraAgricolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilieraAgricolaApplication.class, args);
		System.out.println("HelloWorld!");

	}
}