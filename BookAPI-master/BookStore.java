package com.bcs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.bcs"})
public class BookStore {

	private static final Logger logger = LoggerFactory.getLogger(BookStore.class);
	public static void main(String[] args) {
		SpringApplication.run(BookStore.class, args);
		logger.debug("--Application Started--");
	}
}
