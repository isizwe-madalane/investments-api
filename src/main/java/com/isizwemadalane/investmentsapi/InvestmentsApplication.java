package com.isizwemadalane.investmentsapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class InvestmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestmentsApplication.class, args);
	}

}
