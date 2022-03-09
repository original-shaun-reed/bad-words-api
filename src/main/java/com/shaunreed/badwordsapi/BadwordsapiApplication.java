package com.shaunreed.badwordsapi;


import com.shaunreed.badwordsapi.repository.BadWordsEntity;
import com.shaunreed.badwordsapi.repository.BadWordsRepository;
import com.shaunreed.badwordsapi.service.DatabaseInitiationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BadwordsapiApplication {
	@Autowired
	DatabaseInitiationService dbService;

	public static void main(String[] args) {
		SpringApplication.run(BadwordsapiApplication.class, args);
	}


	@Bean
	CommandLineRunner init (BadWordsRepository badWordsRepository){
		return args -> {
			List<BadWordsEntity> allBadWords = badWordsRepository.findAll();

			if (allBadWords.size() > 0) {
				badWordsRepository.deleteAll();
			}

			List<String> badWords = dbService.getListOfBadWords().getWords();

			for (String badWord : badWords) {
				BadWordsEntity badWordEntity = BadWordsEntity.builder().id(System.nanoTime()).word(badWord).build();
				badWordsRepository.save(badWordEntity);
			}
		};
	}

}
