package it.cs.unicam.ids.filiera.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FilieraAgricolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilieraAgricolaApplication.class, args);
		System.out.println("HelloWorld!");
	}

}
