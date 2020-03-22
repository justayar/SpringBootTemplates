package com.justayar.springboot;

import com.justayar.springboot.util.MapEntryListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HazelcastApplication {

	@Bean
	public MapEntryListener mapEntryListener(){
		return new MapEntryListener();
	}

	public static void main(String[] args) {
		SpringApplication.run(HazelcastApplication.class, args);
	}

}
