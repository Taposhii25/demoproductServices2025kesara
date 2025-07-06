package com.scaler.demoproductservices2025kesara.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Resttemplateconfig {
    //create a bean for resttemplate
    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
