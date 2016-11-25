package com.ddff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.ddff")
public class BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}
}
