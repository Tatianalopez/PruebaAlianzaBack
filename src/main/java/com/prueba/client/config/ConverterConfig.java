package com.prueba.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prueba.client.conversor.ClientConverter;


@Configuration
public class ConverterConfig {
	
	@Value("${config.datetimeFormat}")
	private String datetimeFormat;
	
	@Bean
	public ClientConverter getProductConverter() {
		return new ClientConverter();
	}
	
}
