package com.example.achoclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AchoClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AchoClientApplication.class, args);
	}

}
