package com.project.productservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    // create method that create object of RestTemplate
    // return RestTemplate object
    // we can use the object throughout the application
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder().build();
    }
}
