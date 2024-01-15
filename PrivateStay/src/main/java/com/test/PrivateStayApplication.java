package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.test.entity")
public class PrivateStayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrivateStayApplication.class, args);
	}

}
