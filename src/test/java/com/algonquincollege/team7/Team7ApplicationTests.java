package com.algonquincollege.team7;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class Team7ApplicationTests {

	@Value("${SPRING_APPLICATION_NAME:default_value}")
	private String appName;

	@BeforeAll
	public static void setup() {
		Dotenv dotenv = Dotenv.configure()
				.directory("src/main/resources")
				.ignoreIfMissing()
				.load();

		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);
	}

	@Test
	void testEnvVarLoading() {
		// Verify the .env variable was loaded
		assertEquals("team7", appName,
				"SPRING_APPLICATION_NAME was not loaded from .env");
		System.out.println("SPRING_APPLICATION_NAME: " + appName);
	}

	@Test
	void contextLoads() {
		// Basic Spring context test
		assertTrue(true, "Spring context loaded successfully");
	}
}
