package com.galvanize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarterApplication {
	private static Logger logger = LoggerFactory.getLogger(StarterApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(StarterApplication.class, args);
		logger.info("Application started successfully!");
	}

}
