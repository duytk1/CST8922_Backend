package com.algonquincollege.team7;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Team 7 application.
 *
 * This Spring Boot application initializes the system and loads environment variables
 * from a .env file before starting the application context. Environment variables
 * are made available to Spring's property system.
 *
 * @see SpringBootApplication
 * @since 1.0
 */
@SpringBootApplication
public class Team7Application {

	/**
	 * Main method that launches the Spring Boot application.
	 *
	 * Loads environment variables from .env file and sets them as system properties
	 * before initializing the Spring application context.
	 *
	 * @param args command line arguments passed to the application
	 * @throws io.github.cdimascio.dotenv.DotenvException if there's an error loading the .env file
	 */
	public static void main(String[] args) {
		// Load environment variables from .env file
		Dotenv dotenv = Dotenv.configure().load();

		// Set each environment variable as a system property
		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
		});

		// Start Spring Boot application
		SpringApplication.run(Team7Application.class, args);
	}
}
