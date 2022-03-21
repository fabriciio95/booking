package com.intercab.service.integrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAsync
public class IntegratorApplication {
	public static void main(final String[] args) {
		SpringApplication.run(IntegratorApplication.class, args);
	}
}