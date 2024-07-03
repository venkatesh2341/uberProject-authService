package com.uber_project_auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("com.uber_project.entity_provider.Models")
public class UberProjectAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberProjectAuthServiceApplication.class, args);
	}

}
