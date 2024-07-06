package com.accioJob.QuestionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.yourpackage.repository")
public class QuestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionServiceApplication.class, args);
	}

}
