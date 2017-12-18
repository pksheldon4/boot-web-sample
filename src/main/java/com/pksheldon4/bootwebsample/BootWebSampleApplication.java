package com.pksheldon4.bootwebsample;

import com.pksheldon4.bootwebsample.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootWebSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootWebSampleApplication.class, args);
	}

}
