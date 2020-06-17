package com.studies.jpaquerydsl.settings;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Configuration
public class BeansSettings {

	@Bean
	public ObjectMapper newObjectMapper() {
		return new ObjectMapper()
				.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
				.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
	}
	
}
