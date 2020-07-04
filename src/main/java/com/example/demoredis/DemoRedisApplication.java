package com.example.demoredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRedisApplication.class, args);
	}

}
