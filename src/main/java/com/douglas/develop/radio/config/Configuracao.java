package com.douglas.develop.radio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class Configuracao {

	@Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
