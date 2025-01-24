package com.cart_management.cart_management.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
	
	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
