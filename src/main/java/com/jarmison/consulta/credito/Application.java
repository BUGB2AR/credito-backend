package com.jarmison.consulta.credito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jarmison.consulta.credito")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
