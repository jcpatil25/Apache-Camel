package com.example.apache.camel.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource("classpath:context.xml")
public class ApacheCamelClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApacheCamelClientApplication.class, args);
	}

}
