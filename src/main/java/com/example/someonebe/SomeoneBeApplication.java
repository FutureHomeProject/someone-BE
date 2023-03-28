package com.example.someonebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SomeoneBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SomeoneBeApplication.class, args);
	}

}
