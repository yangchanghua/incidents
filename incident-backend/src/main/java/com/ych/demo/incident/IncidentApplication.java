package com.ych.demo.incident;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class IncidentApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidentApplication.class, args);
	}

}
