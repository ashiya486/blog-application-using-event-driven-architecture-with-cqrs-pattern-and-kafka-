package com.BlogApplication.blogservice.blogquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@SpringBootApplication
public class BlogqueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogqueryApplication.class, args);
	}
	@Bean
	public StringJsonMessageConverter jsonConverter() {
		return new StringJsonMessageConverter();
	}

}
