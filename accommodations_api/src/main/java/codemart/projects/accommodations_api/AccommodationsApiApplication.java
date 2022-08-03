package codemart.projects.accommodations_api;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // equivalent to using @Configuration - @EnableAutoConfiguration - @ComponentScan
public class AccommodationsApiApplication{
	public static void main(String[] args) {
		SpringApplication.run(AccommodationsApiApplication.class, args);
	}
}
