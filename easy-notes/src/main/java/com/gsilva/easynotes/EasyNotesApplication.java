package com.gsilva.easynotes;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableJpaAuditing
public class EasyNotesApplication {
	public static void main(String[] args) {
		run(EasyNotesApplication.class, args);
	}
}
