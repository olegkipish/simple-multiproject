package com.example.loyalty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableAutoConfiguration
@SpringBootApplication
@EnableEurekaClient
public class LoyaltyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoyaltyServiceApplication.class, args);
	}
}
