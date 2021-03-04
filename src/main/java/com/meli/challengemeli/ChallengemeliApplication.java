package com.meli.challengemeli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@PropertySource(value = "classpath:propiedades.properties", encoding = "UTF-8")
public class ChallengemeliApplication {
	
	public static void main(String[] args) {			
		SpringApplication.run(ChallengemeliApplication.class, args);
	}	
}
