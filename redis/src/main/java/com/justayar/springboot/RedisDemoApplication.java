package com.justayar.springboot;

import com.justayar.springboot.configuration.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisDemoApplication {

	@Bean
	@ConfigurationProperties
	public AppConfiguration appConfiguration() { return new AppConfiguration(); }

	public static void main(String[] args) {

		SpringApplication.run(RedisDemoApplication.class, args);
	}

}
