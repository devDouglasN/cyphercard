package com.douglas.mscards;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableRabbit
@Slf4j
public class MscardsApplication {

	public static void main(String[] args) {
		log.info("Information: {}", "test info");
		log.error("Error: {}", "test error");
		log.error("Warning: {}", "test warn");
		SpringApplication.run(MscardsApplication.class, args);
	}

}
