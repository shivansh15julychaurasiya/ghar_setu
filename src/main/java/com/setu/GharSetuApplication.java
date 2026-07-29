package com.setu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.setu.config.JwtProperties;

@EnableJpaAuditing

@SpringBootApplication
public class GharSetuApplication {

	public static void main(String[] args) {
		SpringApplication.run(GharSetuApplication.class, args);
	}

}
