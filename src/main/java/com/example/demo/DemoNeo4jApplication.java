package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.utils.JdbcUtil;

@SpringBootApplication
public class DemoNeo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoNeo4jApplication.class, args);
	}
	@Bean
	public JdbcUtil getJdbcUtil() {
		return new JdbcUtil();
	}
	
}
