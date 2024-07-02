package com.example.pmaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PmaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmaApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOriginPatterns("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("Origin", "Content-Type", "Accept", "Authorization",
								"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
						.exposedHeaders("Origin", "Content-Type", "Accept", "Authorization",
								"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
						.allowCredentials(true);
			}
		};
	}

}
