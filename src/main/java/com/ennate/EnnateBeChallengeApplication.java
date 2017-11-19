package com.ennate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class EnnateBeChallengeApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ennateBeChallengeApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ennateBeChallengeApplication.class);
	}
}
