package com.pollinate.primenumbers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PrimeNumberApplication {

	public static void main(String[] args) {

		SpringApplication.run(PrimeNumberApplication.class, args);
	}

}
