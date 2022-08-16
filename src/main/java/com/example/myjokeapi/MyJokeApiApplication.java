package com.example.myjokeapi;

import com.example.myjokeapi.config.MyJokeInterceptor;
import com.example.myjokeapi.controller.MyJokeController;
import com.example.myjokeapi.service.model.Joke;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class MyJokeApiApplication extends SpringBootServletInitializer implements CommandLineRunner {
	private static Logger log = LoggerFactory.getLogger(MyJokeInterceptor.class);

	@Autowired
	private MyJokeController myJokeController;

	public static void main(String[] args) {
		SpringApplication.run(MyJokeApiApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... arg0) {
		// Run some queries to save jokes in DB
		log.info("Random Joke: " + myJokeController.getRandom().toString());
		log.info("Random Joke: " + myJokeController.getRandom().toString());
		log.info("Random Joke: " + myJokeController.getRandom().toString());
		// Add a new joke in a different category
		log.info("Added Joke: " + myJokeController.addJoke(new Joke("none", "A conference call is the best way for a dozen people to say “bye” 300 times.", "officejoke")).getBody());
		// Check DB entries
		log.info(">>> All Jokes in DB");
		List<Joke> allJokes = myJokeController.allJokes();
		allJokes.forEach ( joke -> {
			log.info("Joke -> " + joke);
		});
	}
}
