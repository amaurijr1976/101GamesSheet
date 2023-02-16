package br.com.games101.sheet.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Games101Config {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
