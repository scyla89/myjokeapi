package com.example.myjokeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MyJokeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyJokeApiApplication.class, args);
	}
}
